package mcl.jejunu.healthapp.formatter;

/**
 * Created by Kim on 2016-06-13.
 */
public class NumberFormatter {

    public static int doubleToInt(double value) {
        return (int)Math.ceil(value);
    }
    public static String doubleToIntString(double value) {
        return String.valueOf((int)Math.ceil(value));
    }
}
