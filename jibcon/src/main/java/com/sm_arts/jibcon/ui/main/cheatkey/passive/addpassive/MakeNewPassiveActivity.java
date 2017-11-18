package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.ActionWeatherData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeNewPassiveActivity extends BaseActivity {
    private static final String TAG = "MakeNewPassiveActivity";

    String mTriggerTime;
    ActionWeatherData mActionWeatherData = null;
    int triggerType = -1;
    int actionType = -1;


    @BindView(R.id.tv_addpassive_action_result)
    TextView mTextViewActionResult;
    @BindView(R.id.tv_addpassive_trigger_result)
    TextView mTextViewTriggerResult;

    @OnClick(R.id.btn_pr_make_new_trigger)
    void goToNewTrigger() {
        Intent intent = new Intent(getApplicationContext(), SelectTriggerActivity.class);
        Log.d(TAG, "goToNewTrigger: ");
        startActivityForResult(intent, IntentCodeEnum.TRIGGER_REQUEST);

    }

    @OnClick(R.id.btn_pr_make_new_action)
    void goToNewAction() {
        Log.d(TAG, "goToNewAction: ");
        Intent intent = new Intent(getApplicationContext(), SelectActionActivity.class);
        startActivityForResult(intent, IntentCodeEnum.ACTION_REQUEST);
    }

    @OnClick(R.id.btn_pr_make_new_ok)
    void makePassiveRoutineFinished() {
        if (triggerType == -1 || actionType == -1) {
            Toast.makeText(getApplicationContext(),"트리거나 액션을 완성해주세요",Toast.LENGTH_SHORT).show();
            return;
        }
        if(triggerType == PassiveRoutineTypeConfig.TriggerTime &&
                actionType == PassiveRoutineTypeConfig.ActionWeather)
        {
            PassiveRoutineManager.getInstance().timeWeater(mTriggerTime,mActionWeatherData);

        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED)
            return;
        switch (resultCode) {
            case IntentCodeEnum.TIME_RESULT:
                mTextViewTriggerResult.setText(data.getStringExtra("SELECTED_TRIGGER_MENT"));
                mTriggerTime = data.getStringExtra("SELECTED_TRIGGER_DATA");
                Toast.makeText(getApplicationContext(), data.getStringExtra("SELECTED_TRIGGER_MENT"), Toast.LENGTH_SHORT).show();
                //make new trigger
                triggerType = PassiveRoutineTypeConfig.TriggerTime;

                break;
            case IntentCodeEnum.WEATHER_RESULT:
                mTextViewActionResult.setText(data.getStringExtra("SELECTED_ACTION_MENT"));
                mActionWeatherData = (ActionWeatherData) data.getSerializableExtra("SELECTED_ACTION_DATA");
                //make new action
                actionType = PassiveRoutineTypeConfig.ActionWeather;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_routine_addpassive_activity);
        ButterKnife.bind(this);
    }
}
