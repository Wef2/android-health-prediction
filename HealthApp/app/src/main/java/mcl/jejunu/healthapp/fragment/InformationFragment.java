package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.activity.BodyModificationActivity;
import mcl.jejunu.healthapp.object.Body;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class InformationFragment extends Fragment {

    private Button modificationButton;
    private TextView heightText, weightText;

    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        heightText = (TextView) view.findViewById(R.id.heightText);
        weightText = (TextView) view.findViewById(R.id.weightText);
        modificationButton = (Button) view.findViewById(R.id.modifyButton);
        modificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BodyModificationActivity.class));
            }
        });
        realm = Realm.getDefaultInstance();
        return view;
    }

    @Override
    public void onResume(){
        if(realm.where(Body.class).findAll().size() > 0){
            Body body = realm.where(Body.class).findAll().last();
            heightText.setText(String.valueOf(body.getHeight()));
            weightText.setText(String.valueOf(body.getWeight()));
        }
        super.onResume();
    }
}