package mcl.jejunu.healthapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import mcl.jejunu.healthapp.R;


public class CurrentActivity extends AppCompatActivity {

    private BarChart chart;
    private ArrayList<String> xVals;

    private int goalValue, currentValue, remainValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);

        goalValue = 10000;
        currentValue = 4000;
        remainValue = goalValue - currentValue;
        if (remainValue < 0) {
            remainValue = 0;
        }

        chart = (BarChart) findViewById(R.id.currentBarChart);

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
        xVals.add("목표"); xVals.add("현재"); xVals.add("잔여");

        BarData data = new BarData(xVals, dataSets);
        chart.setData(data);

        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisLeft().setAxisMinValue(0);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisRight().setEnabled(false);
        chart.setDescription("");
        chart.getLegend().setEnabled(false);
        chart.invalidate();
    }

}