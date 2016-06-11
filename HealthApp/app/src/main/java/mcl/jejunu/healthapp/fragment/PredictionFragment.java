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
import java.util.Date;

import io.realm.Realm;
import mcl.jejunu.healthapp.R;
import mcl.jejunu.healthapp.formatter.StepYAxisValueFormatter;
import mcl.jejunu.healthapp.formatter.DateFormatter;
import mcl.jejunu.healthapp.object.Exercise;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class PredictionFragment extends Fragment {

    private BarChart barChart;
    private long currentValue = 0;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_prediction, container, false);

        barChart = (BarChart) view.findViewById(R.id.barChart);

        realm = Realm.getDefaultInstance();

        currentValue = 0;
        String todayString = DateFormatter.dayFormat(new Date());
        Date today = DateFormatter.toDateDay(todayString);
        Date tomorrow = DateFormatter.theDayAfterXDays(today, 1);

        if (realm.where(Exercise.class).between("date", today, tomorrow).findAll().size() != 0) {
            currentValue = (Long)realm.where(Exercise.class).between("date", today, tomorrow).findAll().sum("count");
        }

        ArrayList<BarEntry> valsUser = new ArrayList<BarEntry>();
        BarEntry goalEntry = new BarEntry(currentValue, 0);
        valsUser.add(goalEntry);
        BarEntry currentEntry = new BarEntry(currentValue * 2, 1);
        valsUser.add(currentEntry);
        BarEntry remainEntry = new BarEntry(currentValue * 7, 2);
        valsUser.add(remainEntry);

        BarDataSet userDataSet = new BarDataSet(valsUser, "사용자");
        userDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        userDataSet.setValueTextSize(10);
        userDataSet.setBarSpacePercent(30);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(userDataSet);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("현재");
        xVals.add("1일 후");
        xVals.add("1주일 후");

        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);

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

}