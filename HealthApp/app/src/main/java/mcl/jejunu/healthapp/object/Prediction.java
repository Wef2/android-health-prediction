package mcl.jejunu.healthapp.object;

/**
 * Created by Kim on 2016-05-14.
 */
public class Prediction {

    private double walksteps;
    private double walkdistance;
    private double heartrate;
    private double pheartrate;
    private double impact;
    private double fatigue;
    private double activity;
    private double respiration;
    private double calorie;
    private double muscular_fitness;

    public double getWalksteps() {
        return walksteps;
    }

    public double getWalkdistance() {
        return walkdistance;
    }

    public double getHeartrate() {
        return heartrate;
    }

    public double getPheartrate() {
        return pheartrate;
    }

    public double getImpact() {
        return impact;
    }

    public double getFatigue() {
        return fatigue;
    }

    public double getActivity() {
        return activity;
    }

    public double getRespiration() {
        return respiration;
    }

    public double getCalorie() {
        return calorie;
    }

    public double getMuscular_fitness() {
        return muscular_fitness;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "walksteps=" + walksteps +
                ", walkdistance=" + walkdistance +
                ", heartrate=" + heartrate +
                ", pheartrate=" + pheartrate +
                ", impact=" + impact +
                ", fatigue=" + fatigue +
                ", activity=" + activity +
                ", respiration=" + respiration +
                ", calorie=" + calorie +
                ", muscular_fitness=" + muscular_fitness +
                '}';
    }
}
