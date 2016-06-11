package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import io.realm.Realm;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.formatter.CalorieYAxisValueFormatter;
import mcl.jejunu.healthapp.formatter.StepYAxisValueFormatter;
import mcl.jejunu.healthapp.formatter.TimeYAxisValueFormatter;
import mcl.jejunu.healthapp.formatter.DateFormatter;
import mcl.jejunu.healthapp.listener.StepUpdateListener;
import mcl.jejunu.healthapp.object.Body;
import mcl.jejunu.healthapp.object.Exercise;
import mcl.jejunu.healthapp.object.Goal;
import mcl.jejunu.healthapp.service.StepCounterService;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class CurrentFragment extends Fragment implements StepUpdateListener {

    private BarChart chart, timeChart, calorieChart;
    private long goalValue, currentValue, remainValue;
    private int stride;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_current, container, false);

        StepCounterService.instance.addStepUpdateListener(this);

        realm = Realm.getDefaultInstance();

        Body body = realm.where(Body.class).findFirst();
        stride = (int) (body.getHeight() * 0.4);

        chart = (BarChart) view.findViewById(R.id.currentBarChart);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisLeft().setAxisMinValue(0);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisLeft().setValueFormatter(new StepYAxisValueFormatter());
        chart.getAxisRight().setEnabled(false);
        chart.setDescription("");
        chart.getLegend().setEnabled(false);

        timeChart = (BarChart) view.findViewById(R.id.timeChart);
        timeChart.getXAxis().setDrawGridLines(false);
        timeChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        timeChart.getAxisLeft().setAxisMinValue(0);
        timeChart.getAxisLeft().setDrawAxisLine(false);
        timeChart.getAxisLeft().setValueFormatter(new TimeYAxisValueFormatter());
        timeChart.getAxisRight().setEnabled(false);
        timeChart.setDescription("");

        calorieChart = (BarChart) view.findViewById(R.id.calorieChart);
        calorieChart.getXAxis().setDrawGridLines(false);
        calorieChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        calorieChart.getAxisLeft().setAxisMinValue(0);
        calorieChart.getAxisLeft().setDrawAxisLine(false);
        calorieChart.getAxisLeft().setValueFormatter(new CalorieYAxisValueFormatter());
        calorieChart.getAxisRight().setEnabled(false);
        calorieChart.setDescription("");


        setCurrentData();

        return view;
    }

    @Override
    public void onStepUpdate() {
        setCurrentData();
    }

    public void setCurrentData(){
        currentValue = 0;
        String todayString = DateFormatter.dayFormat(new Date());
        Date today = DateFormatter.toDateDay(todayString);
        Date tomorrow = DateFormatter.theDayAfterXDays(today, 1);

        if (realm.where(Exercise.class).between("date", today, tomorrow).findAll().size() != 0) {
            currentValue = (Long)realm.where(Exercise.class).between("date", today, tomorrow).findAll().sum("count");
        }
        goalValue = 0;
        if (realm.where(Goal.class).findAll().size() > 0) {
            Goal goal = realm.where(Goal.class).findAll().last();
            goalValue = goal.getSteps();
        }
        remainValue = goalValue - currentValue;
        if (remainValue < 0) {
            remainValue = 0;
        }

        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        valsUser.add(new BarEntry(goalValue, 0));
        valsUser.add(new BarEntry(currentValue, 1));
        valsUser.add(new BarEntry(remainValue, 2));

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
        chart.invalidate();

        ArrayList<BarEntry> valsTime = new ArrayList<BarEntry>();
        valsTime.add(new BarEntry(new float[]{((currentValue * stride / 100) / 70), (((goalValue - currentValue) * stride / 100) / 70)}, 0));

        BarDataSet timeDataSet = new BarDataSet(valsTime, "시간");
        timeDataSet.setStackLabels(new String[]{"현재", "잔여"});
        timeDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        timeDataSet.setValueTextSize(10);
        timeDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> timeDataSets = new ArrayList<IBarDataSet>();
        timeDataSets.add(timeDataSet);

        ArrayList<String> timeXVals = new ArrayList<String>();
        timeXVals.add("시간");
        BarData timeData = new BarData(timeXVals, timeDataSets);
        timeChart.setData(timeData);
        timeChart.invalidate();


        ArrayList<BarEntry> valsCalorie = new ArrayList<BarEntry>();
        valsCalorie.add(new BarEntry(new float[]{((currentValue * stride / 100) / 70) * 3, (((goalValue - currentValue) * stride / 100) / 70) * 3}, 0));

        BarDataSet calorieDataSet = new BarDataSet(valsCalorie, "칼로리");
        calorieDataSet.setStackLabels(new String[]{"현재", "잔여"});
        calorieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        calorieDataSet.setValueTextSize(10);
        calorieDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> calorieDataSets = new ArrayList<IBarDataSet>();
        calorieDataSets.add(calorieDataSet);

        ArrayList<String> calorieXVals = new ArrayList<String>();
        calorieXVals.add("칼로리");
        BarData calorieData = new BarData(calorieXVals, calorieDataSets);
        calorieChart.setData(calorieData);
        calorieChart.invalidate();
    }
}