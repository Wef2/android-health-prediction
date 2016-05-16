package mcl.jejunu.healthapp.object;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Kim on 2016-05-16.
 */
public class Body extends RealmObject{

    private Date date;
    private int height;
    private int weight;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
