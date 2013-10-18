package ro.teamnet.apps.lightlogger.file;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import ro.teamnet.apps.lightlogger.api.Formatter;
import ro.teamnet.apps.lightlogger.format.TimeFormatter;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: mihai.godeanu
 * Date: 10/14/13
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileLoggerTest {
    private FileLogger fileLogger;
    private Formatter formatter;
    private String filePath;

    private static final String NEWLINE = "\n";

    @Before
    public void init(){
        formatter = new TimeFormatter();
        filePath = "fileLoggerTest.out" ;
        fileLogger = new FileLogger(formatter, filePath);
    }

    @Test
    public void file_should_exist(){
        //when
        File file = new File(filePath);
        String line = "Log-ul a fost scris in fisier";
        //then
        fileLogger.log(line);

        //assert
        Assertions.assertThat(file.exists());
    }

    @Test
    public void file_should_contain_log_line() throws IOException {
        //when
        File file = new File(filePath);
        String line = "Log-ul a fost scris in fisier";

        // open input stream test.txt for reading purpose.
        InputStream fIn = null;
        fIn = new FileInputStream(file);

        // create new input stream reader
        InputStreamReader isr = null;
        isr = new InputStreamReader(fIn);

        // create new buffered reader
        BufferedReader br = null;
        br = new BufferedReader(isr);

        //then
        fileLogger.log(line);

        //assert
        Assertions.assertThat(formatter.format(line)).isEqualTo(br.readLine()+NEWLINE);
    }

    @Test
    public void should_log_each_time_on_a_new_line() throws IOException {

        //when
        File file = new File(filePath);
        String line = "Log-ul a fost scris si s-a trecut la o linie noua";
        // open input stream test.txt for reading purpose.
        InputStream fIn = null;
        fIn = new FileInputStream(file);

        // create new input stream reader
        InputStreamReader isr = null;
        isr = new InputStreamReader(fIn);

        // create new buffered reader
        BufferedReader br = null;
        br = new BufferedReader(isr);

        //then
        fileLogger.log(line);

        //assert
        String writtenLine = br.readLine();
        Assertions.assertThat(writtenLine.endsWith(System.lineSeparator()));
    }

    /*@Test
    public void number_of_logs_should_be_equal_to_number_of_lines() throws IOException {

        //when
        String newFilePath = "particularFileLoggerTest.txt";
        File file = new File(newFilePath);
        String line1 = "Log-ul 1";
        String line2 = "Log-ul 2";
        String line3 = "Log-ul 3";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        //then
        fileLogger.log(line1);
        fileLogger.log(line2);
        fileLogger.log(line3);

        //assert
        String writtenLine1 = in.readLine();
        String writtenLine2 = in.readLine();
        String writtenLine3 = in.readLine();

        Assertions.assertThat(writtenLine1).isEqualTo(formatter.format(line1)+System.lineSeparator());
        Assertions.assertThat(writtenLine2).isEqualTo(formatter.format(line2)+System.lineSeparator());
        Assertions.assertThat(writtenLine3).isEqualTo(formatter.format(line3)+System.lineSeparator());
    }*/

    @Test
    public void should_log_to_file() throws IOException {

        filePath = "fileLoggerTest.txt";

        // when
        Formatter formatter = new Formatter() {
            public String format(String line) {
                return line;
            }
        };

        String line = "test";
        fileLogger = new FileLogger(formatter, filePath);

        // then
        fileLogger.log(line);

        // assert
        FileReader reader = new FileReader(filePath);
        char[] buf = new char[line.length()];
        int charsRead = reader.read(buf);
        reader.close();

        Assertions.assertThat(charsRead).isEqualTo(line.length());
        Assertions.assertThat(String.valueOf(buf)).isEqualTo(line);

        Assertions.assertThat(new File(filePath).delete()).isTrue();
    }

    @Test
    public void should_write_text() throws IOException {

        //when
        fileLogger = new FileLogger(new Formatter() {
            public String format(String line) {
                return line;
            }
        }, filePath);

        String line = "test\n";
        fileLogger.log(line);

        //then
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        char[] buf = new char[30];
        reader.read(buf);
        reader.close();

        //assert
        Assertions.assertThat(buf.length).isGreaterThan(1);
        Assertions.assertThat(file.canWrite()).isTrue();
    }
}
