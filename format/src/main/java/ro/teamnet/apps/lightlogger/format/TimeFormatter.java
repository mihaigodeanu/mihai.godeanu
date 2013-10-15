package ro.teamnet.apps.lightlogger.format;

import org.joda.time.DateTime;
import ro.teamnet.apps.lightlogger.api.Formatter;

/**
 * @author dan.cioiu
 * @since 10/2/13
 */
public class TimeFormatter implements Formatter{

    public String format(String line) {
        DateTime now = DateTime.now();
        return String.format("%02d:%02d:%02d - %s\n",
                now.getHourOfDay(), now.getMinuteOfHour(), now.getSecondOfMinute(), line);
    }

}
