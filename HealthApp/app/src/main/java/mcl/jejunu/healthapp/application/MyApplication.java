package mcl.jejunu.healthapp.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
    }

}