package mcl.jejunu.healthapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kim on 2016-05-09.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "health.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE mytable(id integer primary key autoincrement, name text);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table mytable;";
        db.execSQL(sql);
        onCreate(db);
    }
}
