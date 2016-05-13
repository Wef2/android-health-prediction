package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
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

import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.util.SharedPreferenceUtil;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class GoalFragment extends Fragment {

    private EditText stepEditText, strideEditText;
    private TextView distanceText, calorieText, timeText;
    private Button saveButton;
    private int steps = 0, stride = 0;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_goal, container, false);

        steps = SharedPreferenceUtil.getSharedPreference(getActivity(), "steps");
        stride = SharedPreferenceUtil.getSharedPreference(getActivity(), "stride");

        stepEditText = (EditText) view.findViewById(R.id.stepEditText);
        strideEditText = (EditText) view.findViewById(R.id.strideEditText);
        distanceText = (TextView) view.findViewById(R.id.distanceText);
        timeText = (TextView) view.findViewById(R.id.timeText);
        calorieText = (TextView) view.findViewById(R.id.calorieText);

        stepEditText.setText(String.valueOf(steps));
        strideEditText.setText(String.valueOf(stride));
        distanceText.setText(String.valueOf(steps * stride / 100));
        timeText.setText(String.valueOf((steps * stride / 100) / 70));
        calorieText.setText(String.valueOf((steps * stride / 100) / 70 * 3));

        saveButton = (Button) view.findViewById(R.id.saveButton);

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
                distanceText.setText(String.valueOf(steps * stride / 100));
                timeText.setText(String.valueOf((steps * stride / 100) / 70));
                calorieText.setText(String.valueOf((steps * stride / 100) / 70 * 3));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtil.putSharedPreference(getActivity(), "steps", steps);
                SharedPreferenceUtil.putSharedPreference(getActivity(), "stride", stride);
                Snackbar.make(v, "저장 성공!", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}