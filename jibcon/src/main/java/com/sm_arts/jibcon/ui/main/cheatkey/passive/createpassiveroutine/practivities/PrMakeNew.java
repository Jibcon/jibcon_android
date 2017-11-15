package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrMakeNew extends BaseActivity {

    String hour="";
    String minute="";

    String token = FirebaseInstanceId.getInstance().getToken();

    String triggertype ="";
    String actiontype="";

    String lat="";
    String lon="";

    @OnClick(R.id.btn_pr_make_new_trigger) void gototrigger() {
        Intent intent = new Intent(this, PrTriggerSelect.class);
        NotiData notidata = new NotiData(hour, minute, token, triggertype, actiontype, lat, lon);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }

    @OnClick(R.id.btn_pr_make_new_action) void gotoaction() {
        Intent intent = new Intent(this, PrActionSelect.class);
        NotiData notidata = new NotiData(hour, minute, token, triggertype, actiontype, lat, lon);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }

    @BindView(R.id.btn_pr_make_new_trigger)
    Button btnPrMakeNewTrigger;

    @BindView(R.id.btn_pr_make_new_action)
    Button btnPrMakeNewAction;

    @BindView(R.id.btn_pr_make_new_ok)
    Button btnPrMakeNewOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_make_new);
        ButterKnife.bind(this);

        btnPrMakeNewOk.setVisibility(View.GONE);

        Intent intent = getIntent();
        NotiData notidata = (NotiData)intent.getSerializableExtra("come");

        hour = notidata.hour;
        minute = notidata.minute;

        triggertype = notidata.triggertype;
        actiontype = notidata.actiontype;

        lat = notidata.lat;
        lon = notidata.lon;

        Toast.makeText(this, "time "+hour+""+minute
                +"\n token : "+token+"\n trigger, action : "+triggertype+","+actiontype
                +"\nlat, lon :" +lat+" "+lon, Toast.LENGTH_LONG).show();

        if(!triggertype.equals("default")) {
            btnPrMakeNewTrigger.setBackground(getResources().getDrawable(R.drawable.pr_trigger_time));
        }

        if(actiontype.equals("weather")) {
            btnPrMakeNewAction.setBackground(getResources().getDrawable(R.drawable.pr_action_weather));
        }
        else if(actiontype.equals("message")) {
            btnPrMakeNewAction.setBackground(getResources().getDrawable(R.drawable.pr_action_notification));
        }

        if(!(triggertype.equals("default") || actiontype.equals("default")))
        {
            btnPrMakeNewOk.setVisibility(View.VISIBLE);
        }


    }
}
