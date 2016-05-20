package mcl.jejunu.healthapp.application;

import android.app.Application;
import android.content.Intent;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mcl.jejunu.healthapp.service.StepCounterService;

/**
 * Created by Kim on 2016-05-13.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);

        startService(new Intent(MyApplication.this, StepCounterService.class));
    }

    @Override
    public void onTerminate(){
        super.onTerminate();

        stopService(new Intent(MyApplication.this, StepCounterService.class));
    }

}