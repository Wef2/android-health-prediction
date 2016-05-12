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

import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.formatter.MyYAxisValueFormatter;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class CurrentFragment extends Fragment {

    private BarChart chart;
    private int goalValue, currentValue, remainValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current, container, false);

        goalValue = 10000;
        currentValue = 4000;
        remainValue = goalValue - currentValue;
        if (remainValue < 0) {
            remainValue = 0;
        }

        chart = (BarChart) view.findViewById(R.id.currentBarChart);

        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        BarEntry goalEntry = new BarEntry(goalValue, 0);
        valsUser.add(goalEntry);
        BarEntry currentEntry = new BarEntry(currentValue, 1);
        valsUser.add(currentEntry);
        BarEntry remainEntry = new BarEntry(remainValue, 2);
        valsUser.add(remainEntry);

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(userDataSet);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("목표");
        xVals.add("현재");
        xVals.add("잔여");

        BarData data = new BarData(xVals, dataSets);
        chart.setData(data);

        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisLeft().setAxisMinValue(0);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisLeft().setValueFormatter(new MyYAxisValueFormatter());
        chart.getAxisRight().setEnabled(false);
        chart.setDescription("");
        chart.getLegend().setEnabled(false);
        chart.invalidate();
        return view;
    }

}