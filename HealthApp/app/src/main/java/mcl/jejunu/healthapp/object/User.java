package mcl.jejunu.healthapp.object;

import io.realm.RealmObject;

/**
 * Created by neo-202 on 2016-05-10.
 */
public class User extends RealmObject {

    private String name;
    private int height;
    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
