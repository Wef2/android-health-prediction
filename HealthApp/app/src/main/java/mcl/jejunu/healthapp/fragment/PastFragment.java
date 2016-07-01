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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

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
public class PastFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private BarChart barChart;
    private Realm realm;
    private Button selectButton;
    private PopupMenu popupMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past, container, false);

        barChart = (BarChart) view.findViewById(R.id.barChart);

        realm = Realm.getDefaultInstance();

        selectButton = (Button) view.findViewById(R.id.select_button);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu = new PopupMenu(getActivity(), selectButton);
                popupMenu.getMenuInflater().inflate(R.menu.past_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(PastFragment.this);
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

    public void setDataByHour(){
        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

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

        int index = 0;

        for(String key : keyList){

            BarEntry currentBarEntry = new BarEntry(hashtable.get(key), index);
            valsUser.add(currentBarEntry);
            xVals.add(key);
            if(index > 5){
                break;
            }
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

    public void setDataByDay(){
        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

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

        int index = 0;

        for(String key : keyList){

            BarEntry currentBarEntry = new BarEntry(hashtable.get(key), index);
            valsUser.add(currentBarEntry);
            xVals.add(key);
            if(index > 5){
                break;
            }
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

    public void setDataByMonth(){
        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

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

        int index = 0;

        for(String key : keyList){

            BarEntry currentBarEntry = new BarEntry(hashtable.get(key), index);
            valsUser.add(currentBarEntry);
            xVals.add(key);
            if(index > 5){
                break;
            }
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

        return false;
    }
}