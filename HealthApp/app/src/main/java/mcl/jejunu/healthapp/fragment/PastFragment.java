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
public class PastFragment extends Fragment {

    private BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past, container, false);

        barChart = (BarChart) view.findViewById(R.id.barChart);

        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        BarEntry entry1 = new BarEntry(4000, 0);
        valsUser.add(entry1);
        BarEntry entry2 = new BarEntry(8000, 1);
        valsUser.add(entry2);
        BarEntry entry3 = new BarEntry(10000, 2);
        valsUser.add(entry3);
        BarEntry entry4 = new BarEntry(6000, 3);
        valsUser.add(entry4);
        BarEntry entry5 = new BarEntry(12000, 4);
        valsUser.add(entry5);
        BarEntry entry6 = new BarEntry(7000, 5);
        valsUser.add(entry6);
        BarEntry entry7 = new BarEntry(13000, 6);
        valsUser.add(entry7);

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(userDataSet);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("7일 전");
        xVals.add("6일 전");
        xVals.add("5일 전");
        xVals.add("4일 전");
        xVals.add("3일 전");
        xVals.add("2일 전");
        xVals.add("1일 전");

        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setTextSize(7);
        barChart.getAxisLeft().setAxisMinValue(0);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisLeft().setValueFormatter(new MyYAxisValueFormatter());
        barChart.getAxisRight().setEnabled(false);
        barChart.setDescription("");
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();

        return view;
    }

}