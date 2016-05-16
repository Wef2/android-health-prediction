package mcl.jejunu.healthapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import io.realm.Realm;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.object.Goal;

/**
 * Created by Kim on 2016-05-16.
 */
public class GoalModificationActivity extends AppCompatActivity {

    private Realm realm;
    private EditText stepsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_modification);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        stepsText = (EditText)findViewById(R.id.stepsText);

        realm = Realm.getDefaultInstance();
        if(realm.where(Goal.class).findAll().size() > 0){
            Goal goal = realm.where(Goal.class).findAll().last();
            stepsText.setText(String.valueOf(goal.getSteps()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_modification_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Goal goal = realm.createObject(Goal.class);
                        goal.setDate(new Date());
                        goal.setSteps(Integer.parseInt(stepsText.getText().toString()));

                    }
                });
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

