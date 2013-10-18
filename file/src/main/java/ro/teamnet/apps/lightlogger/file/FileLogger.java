package ro.teamnet.apps.lightlogger.file;

import ro.teamnet.apps.lightlogger.api.Formatter;
import ro.teamnet.apps.lightlogger.api.Logger;

import java.io.*;

public class FileLogger implements Logger {

    private Formatter formatter;
    private String filePath;

    public FileLogger(Formatter formatter, String filePath){
        this.formatter = formatter;
        this.filePath = filePath;
    }

    public void log(String line) {
        PrintWriter fOut = null;

        try {
            fOut = new PrintWriter(filePath);
            fOut.print(formatter.format(line));
            fOut.close();
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("File not found");
        }

        //throw new UnsupportedOperationException("to be implemented");
    }
}
