package mcl.jejunu.healthapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.object.Exercise;

public class UserActivity extends AppCompatActivity {

    private Button testButton1, testButton2, testButton3;
    private int count = 0;

    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);

        testButton1 = (Button) findViewById(R.id.testButton1);
        testButton2 = (Button) findViewById(R.id.testButton2);
        testButton3 = (Button) findViewById(R.id.testButton3);

        testButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                realm.beginTransaction();
                Exercise exercise = realm.createObject(Exercise.class);
                exercise.setCount(count);
                exercise.setDate(new Date());
                realm.commitTransaction();
            }
        });

        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Exercise> results = realm.where(Exercise.class).findAll();
                for (int i = 0; i < results.size(); i++) {
                    Log.i("Exercise", results.get(i).toString());
                }
            }
        });

        testButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                realm.where(Exercise.class).findAll().deleteAllFromRealm();
                realm.commitTransaction();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}