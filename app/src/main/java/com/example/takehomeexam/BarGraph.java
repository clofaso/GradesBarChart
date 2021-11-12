package com.example.takehomeexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarGraph extends AppCompatActivity {

    @BindView(R.id.bargraph)
    BarChart gradeChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        //Pulling Data and entering it in Bar Graph
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, (float) intent.getDoubleExtra("A", 0)));
        barEntries.add(new BarEntry(2, (float) intent.getDoubleExtra("B", 0)));
        barEntries.add(new BarEntry(3, (float) intent.getDoubleExtra("C", 0)));
        barEntries.add(new BarEntry(4, (float) intent.getDoubleExtra("D", 0)));
        barEntries.add(new BarEntry(5, (float) intent.getDoubleExtra("F", 0)));

        //X-Axis Labels
        String[] xAxisLabels = new String[]{"", "A", "B", "C", "D", "F"};
        gradeChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
        XAxis xAxis = gradeChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //Setting up bar colors and text
        BarDataSet barDataSet = new BarDataSet(barEntries, "Grade Percentages");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        //Displaying Bar Graph
        BarData grdData = new BarData(barDataSet);
        gradeChart.getDescription().setText("Student Grade Percentages");
        gradeChart.getDescription().setTextSize(16f);
        gradeChart.setFitBars(true);
        gradeChart.setData(grdData);
    }
}