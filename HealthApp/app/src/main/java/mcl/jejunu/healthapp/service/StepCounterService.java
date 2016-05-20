package mcl.jejunu.healthapp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

import io.realm.Realm;
import mcl.jejunu.healthapp.formatter.TodayFormatter;
import mcl.jejunu.healthapp.object.Exercise;

/**
 * Created by Kim on 2016-05-16.
 */
public class StepCounterService extends Service implements SensorEventListener {

    public static final String TAG = "Step Counter Service";

    private SensorManager sensorManager;
    private Sensor sensor;
    private int totalCount = 0;

    private Realm realm;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "CREATE");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);

        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "DESTROY");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i(TAG, "SENSOR CHANGED");
        final String today = TodayFormatter.format(new Date());
        if (realm.where(Exercise.class).equalTo("date", today).findAll().size() == 0) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Exercise exercise = realm.createObject(Exercise.class);
                    exercise.setDate(today);
                    exercise.setCount(1);
                }
            });
        } else {
            final Exercise exercise = realm.where(Exercise.class).equalTo("date", today).findAll().first();
            realm.beginTransaction();
            exercise.setCount(exercise.getCount() + 1);
            realm.commitTransaction();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}