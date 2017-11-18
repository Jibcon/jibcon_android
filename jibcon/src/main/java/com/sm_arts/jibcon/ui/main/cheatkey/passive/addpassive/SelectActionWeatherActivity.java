package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.ActionWeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectActionWeatherActivity extends AppCompatActivity {

    @BindView(R.id.edittext_addpassive_weather_lat)
    EditText mEditTextLat;
    @BindView(R.id.edittext_addpassive_weather_lon)
    EditText mEditTextLon;

    @OnClick(R.id.btn_selectweather_ok)
    void setOnWeatherBtnOk() {

        if(TextUtils.isEmpty(mEditTextLat.getText()) || TextUtils.isEmpty(mEditTextLon.getText()))
            return;

        Intent intent = new Intent();
        ActionWeatherData actionWeatherData = new ActionWeatherData();

        actionWeatherData.lat = mEditTextLat.getText().toString();
        actionWeatherData.lon = mEditTextLon.getText().toString();

        intent.putExtra("SELECTED_ACTION_DATA",actionWeatherData);
        intent.putExtra("SELECTED_ACTION_MENT","위도 "+actionWeatherData.lat + " 경도 "+actionWeatherData.lon+" 의 날씨를 알려줘");
       // Toast.makeText(getApplicationContext(),"위도 "+actionWeatherData.lat + " 경도 "+actionWeatherData.lon+" 의 날씨를 알려줘",Toast.LENGTH_SHORT).show();
        setResult(IntentCodeEnum.WEATHER_RESULT, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_cheatkey_addpassive_selectweather_activity);
        ButterKnife.bind(this);
    }
}
