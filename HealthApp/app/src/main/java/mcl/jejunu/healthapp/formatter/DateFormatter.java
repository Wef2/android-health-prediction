package mcl.jejunu.healthapp.formatter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kim on 2016-05-20.
 */
public class DateFormatter {

    private static DateFormat minuteFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    private static DateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String minuteFormat(Date date) {
        return minuteFormatter.format(date);
    }

    public static String dayFormat(Date date) {
        return dayFormatter.format(date);
    }

    public static Date toDate(String dateString) {
        Date date = null;
        try {
            date = formatter.parse(dateString);

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return date;
    }

}
