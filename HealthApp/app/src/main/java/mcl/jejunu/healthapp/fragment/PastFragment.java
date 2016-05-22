package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.formatter.StepYAxisValueFormatter;
import mcl.jejunu.healthapp.object.Exercise;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class PastFragment extends Fragment {

    private BarChart barChart;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past, container, false);

        barChart = (BarChart) view.findViewById(R.id.barChart);

        realm = Realm.getDefaultInstance();

        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();
        RealmResults<Exercise> exercises = realm.where(Exercise.class).findAll();
        for(int i = 0; i< exercises.size(); i++){
            BarEntry entry = new BarEntry(exercises.get(i).getCount(), i);
            valsUser.add(entry);
            xVals.add(exercises.get(i).getDate());
        }

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(userDataSet);

        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setTextSize(7);
        barChart.getAxisLeft().setAxisMinValue(0);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisLeft().setValueFormatter(new StepYAxisValueFormatter());
        barChart.getAxisRight().setEnabled(false);
        barChart.setDescription("");
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();

        return view;
    }

}