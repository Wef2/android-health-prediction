package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.activity.ModificationActivity;
import mcl.jejunu.healthapp.object.Exercise;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class InformationFragment extends Fragment {

    private Button modificationButton, testButton1, testButton2, testButton3;
    private Realm realm;
    private int count = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_information, container, false);

        modificationButton = (Button) view.findViewById(R.id.modifyButton);
        testButton1 = (Button) view.findViewById(R.id.testButton1);
        testButton2 = (Button) view.findViewById(R.id.testButton2);
        testButton3 = (Button) view.findViewById(R.id.testButton3);

        realm = Realm.getDefaultInstance();

        modificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ModificationActivity.class));
            }
        });

        testButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                Exercise exercise = realm.createObject(Exercise.class);
                exercise.setCount(count);
                exercise.setDate(new Date());
                realm.commitTransaction();
                Snackbar.make(view, "성공 : " + exercise.toString(), Snackbar.LENGTH_SHORT).show();
            }
        });

        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                RealmResults<Exercise> results = realm.where(Exercise.class).findAll();
                for (int i = 0; i < results.size(); i++) {
                    Log.i("Exercise", results.get(i).toString());
                }
                Log.i("Sum : ", String.valueOf(results.sum("count")));
                realm.commitTransaction();
                Snackbar.make(view, "Sum" + String.valueOf(results.sum("count")), Snackbar.LENGTH_SHORT).show();
            }
        });

        testButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                realm.where(Exercise.class).findAll().deleteAllFromRealm();
                realm.commitTransaction();
                Snackbar.make(view, "전부 삭제", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}