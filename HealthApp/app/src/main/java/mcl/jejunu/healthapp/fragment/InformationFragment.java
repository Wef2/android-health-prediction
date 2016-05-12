package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcl.jejunu.healthapp.R;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class InformationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        return view;
    }
}
