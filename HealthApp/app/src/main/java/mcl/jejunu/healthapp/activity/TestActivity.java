package mcl.jejunu.healthapp.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.springframework.web.client.RestTemplate;

import mcl.jejunu.healthapp.R;

public class TestActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = (TextView) findViewById(R.id.textView);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                String url = "http://117.17.102.81:8080/MotionPredictionServer/MotionServlet?method=afterthirtyminutes";
                RestTemplate restTemplate = new RestTemplate();
                String string = restTemplate.getForObject(url, String.class);
                return string;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            progressDialog.dismiss();
            textView.setText(string);
        }

    }

}