package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeNewPassiveActivity extends BaseActivity {
    private static final String TAG = "MakeNewPassiveActivity";

    @BindView(R.id.tv_addpassive_action_result)
    TextView mTextViewActionResult;
    @BindView(R.id.tv_addpassive_trigger_result)
    TextView mTextViewTriggerResult;

    @OnClick(R.id.btn_pr_make_new_trigger)
    void goToNewTrigger() {
        Intent intent = new Intent(getApplicationContext(),SelectTriggerActivity.class);
        Log.d(TAG, "goToNewTrigger: ");
        startActivityForResult(intent, IntentCodeEnum.TRIGGER_REQUEST);

    }

    @OnClick(R.id.btn_pr_make_new_action)
    void goToNewAction() {
        Log.d(TAG, "goToNewAction: ");
        Intent intent = new Intent(getApplicationContext(),SelectActionActivity.class);
        startActivityForResult(intent, IntentCodeEnum.ACTION_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED)
            return;
        switch (resultCode) {
            case IntentCodeEnum.TRIGGER_RESULT:
                mTextViewTriggerResult.setText(data.getStringExtra("SELECTED_MENT"));
                String selectedTime = data.getStringExtra("SELECTED_DATA");
                Toast.makeText(getApplicationContext(),data.getStringExtra("SELECTED_DATA"),Toast.LENGTH_SHORT).show();

                //make new trigger
                break;
            case IntentCodeEnum.ACTION_RESULT:
                //make new action
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
