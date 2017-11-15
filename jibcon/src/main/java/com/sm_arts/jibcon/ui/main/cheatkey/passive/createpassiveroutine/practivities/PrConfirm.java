package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities.remote.APIService;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities.remote.ApiUtils;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrConfirm extends BaseActivity {

    private static final String TAG = "woojin";
    private TextView mResponseTv;
    private APIService mAPIService;

    String hour="";
    String minute="";
    String token="";
    String triggertype ="";
    String actiontype="";

    String lat="";
    String lon="";;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_confirm);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        NotiData notidata = (NotiData)intent.getSerializableExtra("come");

        hour = notidata.hour;
        minute = notidata.minute;
        token = notidata.token;

        triggertype = notidata.triggertype;
        actiontype = notidata.actiontype;

        lat = notidata.lat;
        lon = notidata.lon;

        Button submitBtn = (Button) findViewById(R.id.pr_confirm_finish);

        mAPIService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(hour)) {
                    sendNotidata(hour, minute, token, triggertype, actiontype, lat, lon);
                }
            }
        });

        Toast.makeText(this, "time "+hour+""+minute
                +"\n token : "+token+"\n trigger, action type :"+triggertype+" and "+actiontype
                +"\nlat, lon :" +lat+" "+lon, Toast.LENGTH_LONG).show();
    }


    public void sendNotidata(String hour, String minute, String token, String triggertype,
                             String actiontype, String lat, String lon) {
        mAPIService.saveData(hour, minute, token, triggertype, actiontype, lat, lon).enqueue(new Callback<NotiData>() {
            @Override
            public void onResponse(Call<NotiData> call, Response<NotiData> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "notidata submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<NotiData> call, Throwable t) {
                Log.e(TAG, "Unable to submit notidata to API."+t.toString());
            }
        });
    }



}
