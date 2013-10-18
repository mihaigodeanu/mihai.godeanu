package ro.teamnet.apps.lightlogger.async;

import ro.teamnet.apps.lightlogger.api.Logger;

public class AsyncLogger implements Logger {

    private Logger logger;

    public AsyncLogger(Logger logger){
        this.logger = logger;
    }

    public void log(final String line){
        //shortOperation(line);
        new Thread(new Runnable() {
            public void run(){
                logger.log(line);//longOperation(line);
            }
        }).start();
    }
}
