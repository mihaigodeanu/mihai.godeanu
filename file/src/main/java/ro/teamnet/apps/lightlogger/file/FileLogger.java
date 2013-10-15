package ro.teamnet.apps.lightlogger.file;

import ro.teamnet.apps.lightlogger.api.Formatter;
import ro.teamnet.apps.lightlogger.api.Logger;

import java.io.*;

/**
 * @author dan.cioiu
 * @since 10/2/13
 */
public class FileLogger implements Logger {

    private Formatter formatter;
    private String filePath;

    public FileLogger(Formatter formatter, String filePath){
        this.formatter = formatter;
        this.filePath = filePath;
    }

    public void log(String line) {
        throw new UnsupportedOperationException("to be implemented");
    }
}
