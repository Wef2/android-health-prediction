package mcl.jejunu.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CurrentActivity extends AppCompatActivity {

    private Button predictionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        predictionButton = (Button) findViewById(R.id.predictionButton);

        predictionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentActivity.this, PredictionActivity.class);
                startActivity(intent);
            }
        });
    }
}
