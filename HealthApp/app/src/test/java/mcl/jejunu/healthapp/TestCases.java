package mcl.jejunu.healthapp;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestCases {
    @Test
    public void dateCheck() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        System.out.println(formatter.format(new Date()));
    }

    @Test
    public void dayCheck() throws Exception {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(new Date()));
    }
}