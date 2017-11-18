package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive.trigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive.IntentCodeEnum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTriggerTimeActivity extends AppCompatActivity {

    @BindView(R.id.spinner_selecttime)
    Spinner mSpinner;

    String[] timeItems;

    @OnClick(R.id.btn_selecttime_ok)
    void setTimeOkBtnClicked() {
        String selectedTime = (String) mSpinner.getSelectedItem();
        Intent intent = new Intent();
        intent.putExtra("SELECTED_TRIGGER_DATA",selectedTime);
        intent.putExtra("SELECTED_TRIGGER_MENT","매일 "+selectedTime+" 시간이 되면");
        setResult(IntentCodeEnum.TIME_RESULT,intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_cheatkey_addpassive_selecttime_activity);
        ButterKnife.bind(this);
        timeItems = getResources().getStringArray(R.array.time_options);
        ArrayAdapter<String> timeSpinnerAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                timeItems
        );
        timeSpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_expandable_list_item_1);

        mSpinner.setAdapter(timeSpinnerAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTime = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),selectedTime,Toast.LENGTH_SHORT).show();
                TextView tv = (TextView) parent.getSelectedView();
                tv.setText(selectedTime);
                tv.setTextColor(getResources().getColor(R.color.black_opaque));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
