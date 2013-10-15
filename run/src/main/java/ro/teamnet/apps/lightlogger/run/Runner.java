package ro.teamnet.apps.lightlogger.run;

import ro.teamnet.apps.lightlogger.async.AsyncLogger;
import ro.teamnet.apps.lightlogger.api.Formatter;
import ro.teamnet.apps.lightlogger.api.Logger;
import ro.teamnet.apps.lightlogger.file.FileLogger;
import ro.teamnet.apps.lightlogger.format.TimeFormatter;

/**
 * @author dan.cioiu
 * @since 10/2/13
 */
public class Runner {

    public static void main(String[] args) {

        Formatter formatter = new TimeFormatter();
        Logger logger = new FileLogger(formatter, "test.txt");

        logger.log("This is a test!");
        logger.log("This is another test!");

    }

}
