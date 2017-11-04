package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;


import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrActionSpecific extends BaseActivity {

    String time="default";
    String token="default";
    String message="default";


    @OnClick(R.id.pr_action_specific_setnoti) void gotoconfirm() {
        Intent intent = new Intent(this, PrConfirm.class);
        message = EtPrActionSpecific.getText().toString();
        NotiData notidata = new NotiData(time,token,message);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }

    @BindView(R.id.pr_action_specific_et) EditText EtPrActionSpecific;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_action_specific);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        NotiData notidata = (NotiData)intent.getSerializableExtra("come");

        Log.d("findme",""+notidata.time);
        Log.d("findme",""+notidata.token);
        Log.d("findme",""+notidata.message);

        time = notidata.time;
        token = notidata.token;
        message = notidata.message;
    }
}
