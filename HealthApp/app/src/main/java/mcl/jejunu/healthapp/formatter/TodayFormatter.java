package mcl.jejunu.healthapp.formatter;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kim on 2016-05-20.
 */
public class TodayFormatter{

    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(Date date){
        return formatter.format(date);
    }
}
