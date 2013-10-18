package ro.teamnet.apps.lightlogger.analysis;

import com.google.common.collect.ImmutableSet;
import ro.teamnet.apps.lightlogger.api.LogAnalysisResults;
import ro.teamnet.apps.lightlogger.api.LogAnalyzer;
import ro.teamnet.apps.lightlogger.api.LogSource;
import ro.teamnet.apps.lightlogger.api.LoggedInformation;

import java.util.Date;

/**
 * @author dan.cioiu
 * @since 10/7/13
 */
public class HttpRequestsLogAnalyzer implements LogAnalyzer{

    private LogSource logSource;
    private LogAnalysisResults results = new LogAnalysisResults();

    public HttpRequestsLogAnalyzer(LogSource logSource){
        this.logSource = logSource;
    }

    @Override
    public LogAnalysisResults analyze() {

        String logLine = null;

        while((logLine = logSource.readLine()) != null){
            processLine(logLine);
        }

        // TODO dummy shit
        results.setDateOfAnalysis(new Date());
        results.setTotalLinesAnalyzed(100);
        results.setInfo(ImmutableSet.<LoggedInformation>of(
                new HttpLoggedInformation(HttpMethod.GET, "/logout", 3),
                new HttpLoggedInformation(HttpMethod.GET, "/login", 5),
                new HttpLoggedInformation(HttpMethod.POST, "/print", 2)));

        return results;
    }

    private void processLine(String logLine){

    }
}
