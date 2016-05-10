package mcl.jejunu.healthapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

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

        xVals = new ArrayList<String>();
        xVals.add("목표");
        xVals.add("현재");
        xVals.add("잔여");

        goalValue = 10000;
        currentValue = 4000;
        remainValue = goalValue - currentValue;
        if (remainValue < 0) {
            remainValue = 0;
        }

        chart = (BarChart) findViewById(R.id.currentBarChart);

        ArrayList<BarEntry> valsGoal = new ArrayList<BarEntry>();
        ArrayList<BarEntry> valsCurrent = new ArrayList<BarEntry>();
        ArrayList<BarEntry> valsRemain = new ArrayList<BarEntry>();

        BarEntry goal = new BarEntry(goalValue, 0);
        valsGoal.add(goal);
        BarEntry current = new BarEntry(currentValue, 1);
        valsCurrent.add(current);
        BarEntry remain = new BarEntry(remainValue, 2);
        valsRemain.add(remain);

        BarDataSet goalDataSet = new BarDataSet(valsGoal, "목표");
        goalDataSet.setColor(Color.rgb(217, 80, 138));
        goalDataSet.setValueTextSize(10);
        BarDataSet currentDataSet = new BarDataSet(valsCurrent, "현재");
        currentDataSet.setColor(Color.rgb(254, 149, 7));
        currentDataSet.setValueTextSize(10);
        BarDataSet remainDataSet = new BarDataSet(valsRemain, "잔여");
        remainDataSet.setColor(Color.rgb(254, 247, 120));
        remainDataSet.setValueTextSize(10);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(goalDataSet);
        dataSets.add(currentDataSet);
        dataSets.add(remainDataSet);

        BarData data = new BarData(xVals, dataSets);

        chart.setData(data);

        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisLeft().setAxisMinValue(0);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisRight().setEnabled(false);
        chart.setDescription("");

        chart.invalidate();
    }

}