package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.object.Prediction;

public class TestFragment extends Fragment {

    private TextView textView;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_test, container, false);


        textView = (TextView) view.findViewById(R.id.textView);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        new HttpRequestTask().execute();
        return view;
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Prediction> {
        @Override
        protected Prediction doInBackground(Void... params) {
            try {
                String url = "http://117.17.102.81:8080/MotionPredictionServer/MotionServlet?method=afterthirtyminutes";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Prediction prediction = restTemplate.getForObject(url, Prediction.class);
                return prediction;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Prediction prediction) {
            progressDialog.dismiss();
            textView.setText(prediction.toString());
        }

    }

}