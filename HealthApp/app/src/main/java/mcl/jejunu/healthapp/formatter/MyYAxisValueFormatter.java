package mcl.jejunu.healthapp.formatter;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

/**
 * Created by neo-202 on 2016-05-11.
 */
public class MyYAxisValueFormatter implements YAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        return (int)Math.ceil(value) + "보";
    }
}
