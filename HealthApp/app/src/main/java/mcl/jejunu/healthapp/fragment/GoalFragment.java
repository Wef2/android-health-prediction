package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.activity.GoalModificationActivity;
import mcl.jejunu.healthapp.formatter.NumberFormatter;
import mcl.jejunu.healthapp.object.Body;
import mcl.jejunu.healthapp.object.Goal;
import mcl.jejunu.healthapp.util.SharedPreferenceUtil;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class GoalFragment extends Fragment {

    private TextView stepsText, strideText, distanceText, calorieText, timeText;
    private Button modifyButton;

    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_goal, container, false);

        stepsText = (TextView) view.findViewById(R.id.stepsText);
        strideText = (TextView) view.findViewById(R.id.strideText);
        distanceText = (TextView) view.findViewById(R.id.distanceText);
        timeText = (TextView) view.findViewById(R.id.timeText);
        calorieText = (TextView) view.findViewById(R.id.calorieText);

        modifyButton = (Button) view.findViewById(R.id.modifyButton);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoalModificationActivity.class));
            }
        });

        realm = Realm.getDefaultInstance();
        return view;
    }

    @Override
    public void onResume(){
        if(realm.where(Goal.class).findAll().size() > 0){
            Goal goal = realm.where(Goal.class).findAll().last();
            stepsText.setText(String.valueOf(goal.getSteps()));
            if(realm.where(Body.class).findAll().size() > 0 ){
                Body body = realm.where(Body.class).findAll().last();
                int stride = (int) (body.getHeight() * 0.4);
                strideText.setText(String.valueOf(stride));
                distanceText.setText(String.valueOf(goal.getSteps() * stride / 100));
                timeText.setText(String.valueOf((goal.getSteps() * stride / 100) / 70));
                calorieText.setText(NumberFormatter.doubleToIntString((goal.getSteps() * stride / 100) / 70 * 0.0669 * body.getWeight()));
            }
        }
        super.onResume();
    }

}