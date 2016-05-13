package mcl.jejunu.healthapp.object;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Kim on 2016-05-10.
 */

public class Exercise extends RealmObject {

    private int count;
    private Date date;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
