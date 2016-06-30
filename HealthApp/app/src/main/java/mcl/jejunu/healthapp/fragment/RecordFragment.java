package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.object.Exercise;

/**
 * Created by neo-202 on 2016-06-30.
 */
public class RecordFragment extends Fragment {

    private TextView avgStepsPerMinute, maxStepsPerMinute, totalSteps;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        realm = Realm.getDefaultInstance();
        RealmResults<Exercise> exercises = realm.where(Exercise.class).findAll();
        RealmResults<Exercise> moreThanOneStep = realm.where(Exercise.class).greaterThan("count", 1).findAll();

        avgStepsPerMinute = (TextView) view.findViewById(R.id.avg_steps_per_minute_text);
        maxStepsPerMinute = (TextView) view.findViewById(R.id.max_steps_per_minute_text);
        totalSteps = (TextView) view.findViewById(R.id.total_steps_text);

        avgStepsPerMinute.setText(String.valueOf(moreThanOneStep.average("count")));
        maxStepsPerMinute.setText(String.valueOf(exercises.max("count")));
        totalSteps.setText(String.valueOf(exercises.sum("count")));

        return view;
    }

}
