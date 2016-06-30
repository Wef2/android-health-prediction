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
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.formatter.DateFormatter;
import mcl.jejunu.healthapp.formatter.StepYAxisValueFormatter;
import mcl.jejunu.healthapp.object.Exercise;

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

        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();
        RealmResults<Exercise> exercises = realm.where(Exercise.class).findAllSorted("date");
        Date firstDate = exercises.first().getDate();
        Date lastDate = exercises.last().getDate();

        String firstDateString = DateFormatter.dayFormat(firstDate);
        Date firstDateStart = DateFormatter.toDateDay(firstDateString);
        Date firstDateEnd = DateFormatter.theDayAfterXDays(firstDateStart, 1);

        String lastDateString = DateFormatter.dayFormat(lastDate);
        Date lastDateStart = DateFormatter.toDateDay(lastDateString);
        Date lastDateEnd = DateFormatter.theDayAfterXDays(lastDateStart, 1);

        int index = 0;
        while(!(firstDateStart.equals(lastDateStart))){
            if (realm.where(Exercise.class).between("date", firstDateStart, firstDateEnd).findAll().size() != 0) {
                long count = (Long)realm.where(Exercise.class).between("date", firstDateStart, firstDateEnd).findAll().sum("count");
                BarEntry entry = new BarEntry(count, index);
                valsUser.add(entry);
                xVals.add(DateFormatter.dayFormat(firstDateStart));
                firstDateStart = DateFormatter.theDayAfterXDays(firstDateStart, 1);
                firstDateEnd = DateFormatter.theDayAfterXDays(firstDateEnd, 1);
                index = index + 1;
            }
        }
        if (realm.where(Exercise.class).between("date", lastDateStart, lastDateEnd).findAll().size() != 0) {
            long count = (Long)realm.where(Exercise.class).between("date", lastDateStart, lastDateEnd).findAll().sum("count");
            BarEntry entry = new BarEntry(count, index);
            valsUser.add(entry);
            xVals.add(DateFormatter.dayFormat(lastDateStart));
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu1:
                break;
            case R.id.menu2:
                break;
            case R.id.menu3:
                break;
        }

        return false;
    }
}