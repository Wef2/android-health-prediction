package mcl.jejunu.healthapp.object;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class Goal extends RealmObject {

    private int id;
    private int steps;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
