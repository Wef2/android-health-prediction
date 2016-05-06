package mcl.jejunu.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ExerciseActivity extends AppCompatActivity {

    private Button walkButton, runButton, climbButton, bicycleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        walkButton = (Button) findViewById(R.id.walkButton);
        runButton = (Button) findViewById(R.id.runButton);
        bicycleButton = (Button) findViewById(R.id.bicycleButton);
        climbButton = (Button) findViewById(R.id.climbButton);

        walkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });

        bicycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });

        climbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });


    }
}
