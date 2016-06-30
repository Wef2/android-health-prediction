package mcl.jejunu.healthapp;

import junit.framework.Assert;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import mcl.jejunu.healthapp.object.Exercise;
import mcl.jejunu.healthapp.prediction.PredictionFilter;


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

    @Test
    public void hashMapTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("test", 1);
        hashMap.put("test", 2);
        hashMap.put("test", 3);
        hashMap.put("test", 4);
        hashMap.put("test1", 1);
        Assert.assertEquals(4, hashMap.get("test").intValue());
        Assert.assertEquals(1, hashMap.get("test1").intValue());
    }

    @Test
    public void predictionTest(){
        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise1 = new Exercise();
        Exercise exercise2 = new Exercise();
        exercise1.setDate(new Date());
        exercise1.setCount(10);
        exercise2.setDate(new Date());
        exercise2.setCount(30);

        exercises.add(exercise1);
        exercises.add(exercise2);
        System.out.println(exercises.get(1).toString());
        System.out.println(PredictionFilter.calculate(60, 0, exercises));
    }
}