package mcl.jejunu.healthapp.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.util.DBHelper;

public class UserActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private Button testButton1, testButton2, testButton3;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        dbHelper = new DBHelper(this);

        testButton1 = (Button) findViewById(R.id.testButton1);
        testButton2 = (Button) findViewById(R.id.testButton2);
        testButton3 = (Button) findViewById(R.id.testButton3);

        testButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = formatter.format(new Date());
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.execSQL("INSERT INTO exercise (count, timecheck) VALUES(" + count + ", datetime('now'))");
                database.close();
            }
        });

        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getReadableDatabase();
                Cursor cursor = database.rawQuery("SELECT * FROM exercise", null);
                while(cursor.moveToNext()){
                    Log.i("count", String.valueOf(cursor.getInt(1)));
                    Log.i("date", cursor.getString(2));
                }
                database.close();
            }
        });

        testButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.execSQL("DELETE FROM exercise WHERE count < 100000");
                database.close();
            }
        });
    }
}
