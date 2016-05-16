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
import mcl.jejunu.healthapp.object.Body;

public class BodyModificationActivity extends AppCompatActivity {

    private Realm realm;
    private EditText heightText, weightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_modification);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        heightText = (EditText) findViewById(R.id.heightText);
        weightText = (EditText) findViewById(R.id.weightText);
        realm = Realm.getDefaultInstance();
        if(realm.where(Body.class).findAll().size() > 0){
            Body body = realm.where(Body.class).findAll().last();
            heightText.setText(String.valueOf(body.getHeight()));
            weightText.setText(String.valueOf(body.getWeight()));
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
                        Body body = realm.createObject(Body.class);
                        body.setHeight(Integer.parseInt(heightText.getText().toString()));
                        body.setWeight(Integer.parseInt(weightText.getText().toString()));
                        body.setDate(new Date());
                    }
                });
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
