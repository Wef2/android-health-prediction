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
import mcl.jejunu.healthapp.prediction.PredictionFilter;

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

        String hourString = DateFormatter.hourFormat(new Date());
        Date currentHour = DateFormatter.toDateHour(hourString);
        Date afterOneHour = DateFormatter.theHourAfterXHours(currentHour, 1);

        RealmResults<Exercise> currentExercises = realm.where(Exercise.class).between("date", currentHour, afterOneHour).findAll();

        int index = 0;

        int currentValue = currentExercises.sum("count").intValue();

        BarEntry currentBarEntry = new BarEntry(currentValue, index);
        valsUser.add(currentBarEntry);
        xVals.add(DateFormatter.hourFormat2(currentExercises.first().getDate()));
        index = index + 1;

        Hashtable<String, Integer> hashtable = new Hashtable<>();
        RealmResults<Exercise> allExercises = realm.where(Exercise.class).findAll();

        for (Exercise exercise : allExercises) {
            String dateString = DateFormatter.hourFormat(exercise.getDate());
            Integer count = hashtable.get(dateString);
            if (count == null) {
                count = Integer.valueOf(0);
            }
            count = count + exercise.getCount();
            hashtable.put(dateString, count);
        }

        List<String> keyList = new ArrayList<>(hashtable.keySet());
        Collections.sort(keyList);
        List<Integer> integers = new ArrayList<>();
        for (String key : keyList){
            integers.add(hashtable.get(key));
        }

        for (int i = 1; i <= 3; i++){
            int predictValue = (int)PredictionFilter.predict(i, currentValue, integers);
            BarEntry predictionBarEntry = new BarEntry(predictValue, index);
            valsUser.add(predictionBarEntry);
            xVals.add(i+"시간 후");
            index = index + 1;
        }

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(userDataSet);

        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);
        barChart.invalidate();
    }

    public void setDataByDay() {
        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        String dayString = DateFormatter.dayFormat(new Date());
        Date currentDay = DateFormatter.toDateDay(dayString);
        Date afterOneDay = DateFormatter.theDayAfterXDays(currentDay, 1);

        RealmResults<Exercise> currentExercises = realm.where(Exercise.class).between("date", currentDay, afterOneDay).findAll();

        int index = 0;

        int currentValue = currentExercises.sum("count").intValue();

        BarEntry currentBarEntry = new BarEntry(currentValue, index);
        valsUser.add(currentBarEntry);
        xVals.add(DateFormatter.dayFormat2(currentExercises.first().getDate()));
        index = index + 1;

        Hashtable<String, Integer> hashtable = new Hashtable<>();
        RealmResults<Exercise> allExercises = realm.where(Exercise.class).findAll();

        for (Exercise exercise : allExercises) {
            String dateString = DateFormatter.dayFormat(exercise.getDate());
            Integer count = hashtable.get(dateString);
            if (count == null) {
                count = Integer.valueOf(0);
            }
            count = count + exercise.getCount();
            hashtable.put(dateString, count);
        }

        List<String> keyList = new ArrayList<>(hashtable.keySet());
        Collections.sort(keyList);
        List<Integer> integers = new ArrayList<>();
        for (String key : keyList){
            integers.add(hashtable.get(key));
        }

        for (int i = 1; i <= 3; i++){
            int predictValue = (int)PredictionFilter.predict(i, currentValue, integers);
            BarEntry predictionBarEntry = new BarEntry(predictValue, index);
            valsUser.add(predictionBarEntry);
            xVals.add(i+"일 후");
            index = index + 1;
        }

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(userDataSet);

        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);
        barChart.invalidate();
    }

    public void setDataByMonth() {
        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        String dayString = DateFormatter.monthFormat(new Date());
        Date currentMonth = DateFormatter.toDateMonth(dayString);
        Date afterOneMonth = DateFormatter.theMonthAfterXMonths(currentMonth, 1);

        RealmResults<Exercise> currentExercises = realm.where(Exercise.class).between("date", currentMonth, afterOneMonth).findAll();

        int index = 0;

        int currentValue = currentExercises.sum("count").intValue();

        BarEntry currentBarEntry = new BarEntry(currentValue, index);
        valsUser.add(currentBarEntry);
        xVals.add(DateFormatter.monthFormat2(currentExercises.first().getDate()));
        index = index + 1;

        Hashtable<String, Integer> hashtable = new Hashtable<>();
        RealmResults<Exercise> allExercises = realm.where(Exercise.class).findAll();

        for (Exercise exercise : allExercises) {
            String dateString = DateFormatter.monthFormat(exercise.getDate());
            Integer count = hashtable.get(dateString);
            if (count == null) {
                count = Integer.valueOf(0);
            }
            count = count + exercise.getCount();
            hashtable.put(dateString, count);
        }

        List<String> keyList = new ArrayList<>(hashtable.keySet());
        Collections.sort(keyList);
        List<Integer> integers = new ArrayList<>();
        for (String key : keyList){
            integers.add(hashtable.get(key));
        }

        for (int i = 1; i <= 3; i++){
            int predictValue = (int)PredictionFilter.predict(i, currentValue, integers);
            BarEntry predictionBarEntry = new BarEntry(predictValue, index);
            valsUser.add(predictionBarEntry);
            xVals.add(i+"개월 후");
            index = index + 1;
        }

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
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