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

/**
 * Created by neo-202 on 2016-05-11.
 */
public class PastFragment extends Fragment {

    private static PastFragment newInstance = null;

    public static PastFragment getInstance() {
        if (newInstance == null) {
            newInstance = new PastFragment();
        }
        return newInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past, container, false);
        return view;
    }

}