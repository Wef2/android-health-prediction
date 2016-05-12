package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.util.SharedPreferenceUtil;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class GoalFragment extends Fragment {

    private EditText stepEditText, strideEditText;
    private TextView distanceText, calorieText, timeText;
    private int steps = 0, stride = 0;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal, container, false);

        stepEditText = (EditText) view.findViewById(R.id.stepEditText);
        strideEditText = (EditText) view.findViewById(R.id.strideEditText);

        distanceText = (TextView) view.findViewById(R.id.distanceText);
        timeText = (TextView) view.findViewById(R.id.timeText);
        calorieText = (TextView) view.findViewById(R.id.calorieText);

        stepEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (stepEditText.getText().length() == 0) {
                    steps = 0;
                } else {
                    steps = Integer.valueOf(stepEditText.getText().toString());
                }
                SharedPreferenceUtil.putSharedPreference(getActivity(), "steps", steps);
                distanceText.setText(String.valueOf(steps * stride / 100));
                timeText.setText(String.valueOf((steps * stride / 100) / 70));
                calorieText.setText(String.valueOf((steps * stride / 100) / 70 * 3));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        strideEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (strideEditText.getText().length() == 0) {
                    stride = 0;
                } else {
                    stride = Integer.valueOf(strideEditText.getText().toString());
                }
                SharedPreferenceUtil.putSharedPreference(getActivity(), "stride", stride);
                distanceText.setText(String.valueOf(steps * stride / 100));
                timeText.setText(String.valueOf((steps * stride / 100) / 70));
                calorieText.setText(String.valueOf((steps * stride / 100) / 70 * 3));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

}