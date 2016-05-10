package mcl.jejunu.healthapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mcl.jejunu.healthapp.R;

public class GoalActivity extends AppCompatActivity {

    private Button inputButton;
    private EditText stepEditText, strideEditText;
    private TextView distanceText, calorieText , timeText;
    private int step = 0, stride = 0, minute = 0, calorie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        stepEditText = (EditText) findViewById(R.id.stepEditText);
        strideEditText = (EditText) findViewById(R.id.strideEditText);

        distanceText = (TextView) findViewById(R.id.distanceText);
        timeText = (TextView) findViewById(R.id.timeText);
        calorieText = (TextView) findViewById(R.id.calorieText);

        stepEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(stepEditText.getText().length() == 0){
                    step = 0;
                }
                else{
                    step = Integer.valueOf(stepEditText.getText().toString());
                }
                distanceText.setText(String.valueOf(step * stride / 100));
                timeText.setText(String.valueOf((step * stride / 100) / 70));
                calorieText.setText(String.valueOf((step * stride / 100) / 70 * 3));
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
                if(strideEditText.getText().length() == 0){
                    stride = 0;
                }
                else{
                    stride = Integer.valueOf(strideEditText.getText().toString());
                }
                distanceText.setText(String.valueOf(step * stride / 100));
                timeText.setText(String.valueOf((step * stride / 100) / 70));
                calorieText.setText(String.valueOf((step * stride / 100) / 70 * 3));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputButton = (Button) findViewById(R.id.inputButton);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoalActivity.this, PredictionActivity.class);
                startActivity(intent);
            }
        });
    }
}