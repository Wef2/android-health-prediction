package mcl.jejunu.healthapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.formatter.DateFormatter;
import mcl.jejunu.healthapp.formatter.StepYAxisValueFormatter;
import mcl.jejunu.healthapp.object.Exercise;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class PredictionFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private BarChart barChart;
    private Realm realm;
    private Button selectButton;
    private PopupMenu popupMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_prediction, container, false);

        barChart = (BarChart) view.findViewById(R.id.barChart);

        realm = Realm.getDefaultInstance();

        selectButton = (Button) view.findViewById(R.id.select_button);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu = new PopupMenu(getActivity(), selectButton);
                popupMenu.getMenuInflater().inflate(R.menu.prediction_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(PredictionFragment.this);
                popupMenu.show();
            }
        });

        setDataByHour();

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisLeft().setAxisMinValue(0);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisLeft().setValueFormatter(new StepYAxisValueFormatter());
        barChart.getAxisRight().setEnabled(false);
        barChart.setDescription("");
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();
        return view;
    }

    public void setDataByHour() {
        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        Hashtable<String, AtomicInteger> hourHashtable = new Hashtable<>();

        String hourString = DateFormatter.hourFormat2(new Date());
        Date currentHour = DateFormatter.toDateHour(hourString);
        Date afterOneHour = DateFormatter.theHourAfterXHours(currentHour, 1);

        RealmResults<Exercise> currentExercies = realm.where(Exercise.class).between("date", currentHour, afterOneHour).findAll();

        int index = 0;

        int currentValue = currentExercies.sum("count").intValue();

        BarEntry currentBarEntry = new BarEntry(currentValue, index);
        valsUser.add(currentBarEntry);
        xVals.add(DateFormatter.hourFormat(currentExercies.first().getDate()));
        index = index + 1;

        for (int i = 1; i <= 3; i++){
//            int predictValue = PredictionFilter.predict(i,currentValue, )
            BarEntry predictionBarEntry = new BarEntry(i, index);
            valsUser.add(predictionBarEntry);
            xVals.add(i+"시간 후");
            index = index + 1;
        }

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(userDataSet);

        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);
        barChart.invalidate();
    }

    public void setDataByDay() {
        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        Hashtable<String, AtomicInteger> dayHashtable = new Hashtable<>();

        String todayString = DateFormatter.dayFormat(new Date());
        Date today = DateFormatter.toDateDay(todayString);
        Date tomorrow = DateFormatter.theDayAfterXDays(today, 1);

        RealmResults<Exercise> todayExercises = realm.where(Exercise.class).between("date", today, tomorrow).findAll();

        for (Exercise exercise : todayExercises) {
            String dateString = DateFormatter.dayFormat(exercise.getDate());
            AtomicInteger count = dayHashtable.get(dateString);
            if (count == null) {
                count = new AtomicInteger(0);
            }
            count.addAndGet(exercise.getCount());
            dayHashtable.put(dateString, count);
        }

        List<String> keyList = new ArrayList<String>(dayHashtable.keySet());
        Collections.sort(keyList);

        int index = 0;
        for (String key : keyList){
            BarEntry barEntry = new BarEntry(dayHashtable.get(key).get(), index);
            valsUser.add(barEntry);
            xVals.add(key);
            index = index + 1;
        }

        for (int i = 1; i <= 3; i++){
            BarEntry barEntry = new BarEntry(i, index);
            valsUser.add(barEntry);
            xVals.add(i+"일 후");
            index = index + 1;
        }

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(userDataSet);

        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);
        barChart.invalidate();
    }

    public void setDataByMonth() {
        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        Hashtable<String, AtomicInteger> hourHashtable = new Hashtable<>();

        RealmResults<Exercise> exercises = realm.where(Exercise.class).findAll();

        for (Exercise exercise : exercises) {
            String dateString = DateFormatter.monthFormat(exercise.getDate());
            AtomicInteger count = hourHashtable.get(dateString);
            if (count == null) {
                count = new AtomicInteger(0);
            }
            count.addAndGet(exercise.getCount());
            hourHashtable.put(dateString, count);
        }

        List<String> keyList = new ArrayList<String>(hourHashtable.keySet());
        Collections.sort(keyList);

        int index = 0;
        for (String key : keyList){
            BarEntry barEntry = new BarEntry(hourHashtable.get(key).get(), index);
            valsUser.add(barEntry);
            xVals.add(key);
            index = index + 1;
        }

        for (int i = 1; i <= 3; i++){
            BarEntry barEntry = new BarEntry(i, index);
            valsUser.add(barEntry);
            xVals.add(i+"개월 후");
            index = index + 1;
        }

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(userDataSet);

        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);
        barChart.invalidate();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu1:
                setDataByHour();
                break;
            case R.id.menu2:
                setDataByDay();
                break;
            case R.id.menu3:
                setDataByMonth();
                break;
        }

        return true;
    }
}