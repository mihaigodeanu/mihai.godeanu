package ro.teamnet.apps.lightlogger.console;

import ro.teamnet.apps.lightlogger.api.Formatter;
import ro.teamnet.apps.lightlogger.api.Logger;

/**
 * @author dan.cioiu
 * @since 10/4/13
 */
public class ConsoleLogger implements Logger {

    private Formatter formatter;

    public ConsoleLogger(Formatter formatter){
        this.formatter = formatter;
    }

    public void log(String line) {
        System.out.print(formatter.format(line));
    }
}
