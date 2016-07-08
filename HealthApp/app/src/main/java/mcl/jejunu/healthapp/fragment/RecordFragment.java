package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.formatter.DateFormatter;
import mcl.jejunu.healthapp.object.Exercise;

/**
 * Created by neo-202 on 2016-06-30.
 */
public class RecordFragment extends Fragment {

    private TextView avgStepsPerMinute, maxStepsPerMinute, avgStepsPerDay, maxStepsPerDay, totalSteps;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        realm = Realm.getDefaultInstance();
        RealmResults<Exercise> exercises = realm.where(Exercise.class).findAll();
        RealmResults<Exercise> moreThanOneStep = realm.where(Exercise.class).greaterThan("count", 1).findAll();

        avgStepsPerMinute = (TextView) view.findViewById(R.id.avg_steps_per_minute_text);
        maxStepsPerMinute = (TextView) view.findViewById(R.id.max_steps_per_minute_text);
        avgStepsPerDay = (TextView) view.findViewById(R.id.avg_steps_per_day_text);
        maxStepsPerDay = (TextView) view.findViewById(R.id.max_steps_per_day_text);
        totalSteps = (TextView) view.findViewById(R.id.total_steps_text);

        RealmResults<Exercise> allExercises = realm.where(Exercise.class).findAll();
        Hashtable<String, Integer> hashtable = new Hashtable<>();

        for (Exercise exercise : allExercises) {
            String dateString = DateFormatter.dayFormat(exercise.getDate());
            Integer count = hashtable.get(dateString);
            if (count == null) {
                count = Integer.valueOf(0);
            }
            count = count + exercise.getCount();
            hashtable.put(dateString, count);
        }

        Collection<Integer> numbers = hashtable.values();
        int maxStepsPerDayNumber = 0;
        int total = 0;
        int avgStepsPerDayNumber;
        for(Integer integer : numbers){
            if(integer > maxStepsPerDayNumber){
                maxStepsPerDayNumber = integer;
            }
            total = total + integer;
        }
        avgStepsPerDayNumber = (total / numbers.size());

        avgStepsPerMinute.setText(String.valueOf((int)moreThanOneStep.average("count")));
        maxStepsPerMinute.setText(String.valueOf(exercises.max("count")));
        avgStepsPerDay.setText(String.valueOf(avgStepsPerDayNumber));
        maxStepsPerDay.setText(String.valueOf(maxStepsPerDayNumber));
        totalSteps.setText(String.valueOf(exercises.sum("count")));

        return view;
    }

}
