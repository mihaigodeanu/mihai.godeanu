package ro.teamnet.apps.lightlogger.console;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import ro.teamnet.apps.lightlogger.api.Formatter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author dan.cioiu
 * @since 10/4/13
 */
public class ConsoleLoggerTest {

    private ConsoleLogger logger;

    @Test
    public void should_log_to_console(){
        // when
        logger = new ConsoleLogger(new Formatter() {
            @Override
            public String format(String line) {
                return line;
            }
        });
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buff));

        // then
        logger.log("line");

        //
        String expected = "line" + System.getProperty("line.separator");
        Assertions.assertThat(buff.toByteArray()).isEqualTo(expected.getBytes());
    }
}
