package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive.action;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive.IntentCodeEnum;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectActionActivity extends AppCompatActivity {
    private static final String TAG = "SelectActionActivity";
    @OnClick(R.id.btn_pr_action_select_popular_time)
    void onTimeSelected()
    {
        Intent intent = new Intent(getApplicationContext(),SelectActionWeatherActivity.class);
        startActivityForResult(intent, IntentCodeEnum.WEATHER_REQUEST);
    }
    @OnClick(R.id.btn_selectaction_ok)
    void onCheckBtnClicked()
    {
        setResult(IntentCodeEnum.ACTION_REQUEST/*Trigger Data*/);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.main_cheatkey_addpassive_selectaction_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED)
            return;
        switch (resultCode)
        {
            case IntentCodeEnum.WEATHER_RESULT :
                setResult(IntentCodeEnum.WEATHER_RESULT,data);
                finish();
                break;

        }
    }
}
