package ro.teamnet.apps.lightlogger.async;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ro.teamnet.apps.lightlogger.api.Formatter;
import ro.teamnet.apps.lightlogger.api.Logger;
import ro.teamnet.apps.lightlogger.file.FileLogger;
import ro.teamnet.apps.lightlogger.format.TimeFormatter;

import java.io.File;

public class AsyncLoggerTest {

    private AsyncLogger asyncLogger;
    private FileLogger fileLogger;
    private Formatter formatter;
    private String filePath;

    @Before
    public void init(){
        formatter = new TimeFormatter();
        filePath = "asyncLoggerTest.out" ;
        fileLogger = new FileLogger(formatter, filePath);
        asyncLogger = new AsyncLogger(fileLogger);
    }

    @Test
    public void should_log_in_file(){

        //when
        String line = "Log asincron scris in fisier";
        File file = new File(filePath);

        //then
        asyncLogger.log(line);

        //assert
        Assertions.assertThat(file.exists());
    }

    @Test
    public void should_log_async(){

        final long sleepTime = 5000;//5 sec

        // when - conditiile initiale
        Logger asyncLogger = new AsyncLogger(new Logger() {
            public void log(String line) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("Thread creation error");
                }
            }
        });
        String line = "Async Log";

        // then - actiunea efectiva
        long startTime = System.currentTimeMillis();
        asyncLogger.log(line);
        long endTime = System.currentTimeMillis();

        // assert - verificare corectitudine rezultat
        long diff = endTime - startTime;
        Assertions.assertThat(diff).isLessThan(sleepTime);
    }

    @Test
    public void should_call_delegate() throws InterruptedException {
        // when
        UnTest unTest = new UnTest();
        asyncLogger = new AsyncLogger(unTest);

        // then
        asyncLogger.log("Logam un test");

        // punem 1 sec de "sleep" in thread sa vedem dc se apeleaza Logger-ul din UnTest intre timp.
        Thread.sleep(1000L);

        // assert
        Assertions.assertThat(unTest.callCount).isEqualTo(1);
    }

    static class UnTest implements Logger {
        int callCount = 0;

        public void log(String line) {
            callCount ++;
        }
    }

    @Test
    public void should_log_async_using_mock() throws InterruptedException{

        //when
        Logger asyncLogger = Mockito.mock(AsyncLogger.class);

        //then
        asyncLogger.log("myLog");

        //assert
        Mockito.verify(asyncLogger).log("myLog");

        // when
        Logger genericLogger = Mockito.mock(Logger.class);
        asyncLogger = new AsyncLogger(genericLogger);

        // then
        asyncLogger.log("myLog");

        // punem 1 sec de "sleep" in thread sa vedem dc se apeleaza Logger-ul intre timp.
        Thread.sleep(1000L);

        // assert
        Mockito.verify(genericLogger).log("myLog");
    }
}
