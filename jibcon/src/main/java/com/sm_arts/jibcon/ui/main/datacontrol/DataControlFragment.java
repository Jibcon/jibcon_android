package com.sm_arts.jibcon.ui.main.datacontrol;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.repository.helper.WeatherMessageManager;
import com.sm_arts.jibcon.utils.customview.MyMarkerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * Created by Chanjoo on 2017-03-30.
 */

public class DataControlFragment extends android.support.v4.app.Fragment implements DataControllView, OnChartValueSelectedListener {
    private final String TAG = "jibcon/" + getClass().getSimpleName();


//    @BindView(R.id.main_datacontrol_day_tv)
//    TextView mDayTextView;
//    @BindView(R.id.main_datacontrol_hour_tv)
//    TextView mHourTextView;
    @BindView(R.id.main_datacontrol_temperatureChart_lineChart)
    LineChart mTemperatureChart;
    @BindView(R.id.main_datacontrol_humidityChart_lineChart)
    LineChart mHumidityChart;

    WeatherMessageManager messageManager;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.maindatacontrol_datacontrol_fragment, container, false);
        ButterKnife.bind(this, view);

        messageManager = new WeatherMessageManager(this);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.main_datacontrol_swipelayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                messageManager.getCurrentWeather();
            }
        });

        messageManager.getCurrentWeather();
        return view;
    }


    @Override
    public void onDownloadReady() {

    }

    @Override
    public void onDownloadFinished() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void drawingWeatherChart(LinkedTreeMap<String, Object> map) {
        Log.d(TAG, "drawingWeatherChart: ");
        ArrayList<LinkedTreeMap<String, Object>> weatherDatas = (ArrayList<LinkedTreeMap<String, Object>>) map.get("data");

        mTemperatureChart.getDescription().setText("");
        mTemperatureChart.getAxisLeft().setDrawGridLines(false);
        mTemperatureChart.getXAxis().setDrawGridLines(false);
        mTemperatureChart.getXAxis().setDrawAxisLine(false);
        mTemperatureChart.getAxisLeft().setDrawAxisLine(false);
        mTemperatureChart.setDrawGridBackground(false);
        mTemperatureChart.getAxisLeft().setEnabled(false);
        mTemperatureChart.getAxisRight().setEnabled(false);
//        lineChart.getXAxis().setEnabled(false);
        //lineChart.getLegend().setEnabled(false);


        mTemperatureChart.setOnChartValueSelectedListener(this);
        mTemperatureChart.setTouchEnabled(true);
        mTemperatureChart.animateY(1000, Easing.EasingOption.EaseInCubic);

        ArrayList<Entry> entries = new ArrayList<>();


        final ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            LinkedTreeMap<String, Object> timeData = (LinkedTreeMap<String, Object>) weatherDatas.get(i).get("time");
            LinkedTreeMap<String, Object> weatherData = (LinkedTreeMap<String, Object>) weatherDatas.get(i).get("temperature");

            entries.add(new Entry((float) i, Float.parseFloat((String) weatherData.get("tc"))));
            String[] arr = ((String) timeData.get("to")).split("-");

            labels.add(arr[2].substring(3, 8));

        }
        LineDataSet lineDataSet = new LineDataSet(entries, "온도(습도)");

        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setValueTextColor(Color.BLUE);
        lineDataSet.setDrawValues(true);
//        lineDataSet.setDrawFilled(true);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setFillAlpha(10);

        lineDataSet.enableDashedLine(10f, 5f, 0f);
        lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSet.setLineWidth(1f);

        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        lineDataSet.setFormSize(15.f);


        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_blue);
            lineDataSet.setFillDrawable(drawable);
        }


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(lineDataSet);

        LineData lineData = new LineData(dataSets);
        XAxis xAxis = mTemperatureChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d(TAG, "getFormattedValue: " + (int) value);
                return labels.get((int) value);
            }
        });
        xAxis.setGranularity(1f);

        mTemperatureChart.setData(lineData);

    }

    @Override
    public void drawingHumidityChart(LinkedTreeMap<String, Object> body) {
        ArrayList<LinkedTreeMap<String, Object>> weatherDatas = (ArrayList<LinkedTreeMap<String, Object>>) body.get("data");

        mHumidityChart.getDescription().setText("");
        mHumidityChart.getAxisLeft().setDrawGridLines(false);
        mHumidityChart.getXAxis().setDrawGridLines(false);
        mHumidityChart.getXAxis().setDrawAxisLine(false);
        mHumidityChart.getAxisLeft().setDrawAxisLine(false);
        mHumidityChart.setDrawGridBackground(false);
        mHumidityChart.getAxisLeft().setEnabled(false);
        mHumidityChart.getAxisRight().setEnabled(false);
//        lineChart.getXAxis().setEnabled(false);
        //lineChart.getLegend().setEnabled(false);


        mHumidityChart.setOnChartValueSelectedListener(this);
        mHumidityChart.setTouchEnabled(true);
        mHumidityChart.animateY(1000, Easing.EasingOption.EaseInCubic);

        ArrayList<Entry> entries = new ArrayList<>();


        final ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            LinkedTreeMap<String, Object> timeData = (LinkedTreeMap<String, Object>) weatherDatas.get(i).get("time");
            String humidity = (String) weatherDatas.get(i).get("humidity");

            entries.add(new Entry((float) i, Float.parseFloat(humidity)));
            String[] arr = ((String) timeData.get("to")).split("-");

            labels.add(arr[2].substring(3, 8));

        }
        LineDataSet lineDataSet = new LineDataSet(entries, "습도(%)");

        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setValueTextColor(Color.RED);
        lineDataSet.setDrawValues(true);
//        lineDataSet.setDrawFilled(true);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setFillAlpha(10);

        lineDataSet.enableDashedLine(10f, 5f, 0f);
        lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSet.setLineWidth(1f);

        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        lineDataSet.setFormSize(15.f);


        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_blue);
            lineDataSet.setFillDrawable(drawable);
        }


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(lineDataSet);

        LineData lineData = new LineData(dataSets);
        XAxis xAxis = mHumidityChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d(TAG, "getFormattedValue: " + (int) value);
                return labels.get((int) value);
            }
        });
        xAxis.setGranularity(1f);

        mHumidityChart.setData(lineData);

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
