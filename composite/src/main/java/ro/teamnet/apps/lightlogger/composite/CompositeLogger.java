package ro.teamnet.apps.lightlogger.composite;

import ro.teamnet.apps.lightlogger.api.Logger;

import java.util.Set;

/**
 * @author dan.cioiu
 * @since 10/7/13
 */
public class CompositeLogger implements Logger{

    private Set<Logger> loggers;

    public CompositeLogger(Set<Logger> loggers){
        this.loggers = loggers;
    }

    @Override
    public void log(final String line) {
        for(final Logger l: loggers){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    l.log(line);
                }
            }).start();
        }
    }
}
