package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sm_arts.jibcon.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTriggerActivity extends AppCompatActivity {
    private static final String TAG = "SelectTriggerActivity";
    @OnClick(R.id.btn_pr_trigger_select_popular_time)
    void onTimeSelected()
    {
        Intent intent = new Intent(getApplicationContext(),SelectTriggerTimeActivity.class);
        startActivityForResult(intent,IntentCodeEnum.TIME_REQUEST);
    }
    @OnClick(R.id.btn_selecttrigger_ok)
    void onCheckBtnClicked()
    {
        setResult(IntentCodeEnum.TRIGGER_REQUEST/*Trigger Data*/);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.main_cheatkey_addpassive_selecttirgger_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED)
            return;
        switch (resultCode)
        {
            case IntentCodeEnum.TIME_RESULT :
                setResult(IntentCodeEnum.TRIGGER_RESULT,data);
                finish();
                break;

        }
    }
}
