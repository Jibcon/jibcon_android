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

    String time="default";
    String token="default";
    String message="default";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_confirm);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        NotiData notidata = (NotiData)intent.getSerializableExtra("come");


        Log.d("findme",""+notidata.time);
        Log.d("findme",""+notidata.token);
        Log.d("findme",""+notidata.message);

        time = notidata.time;
        token = notidata.token;
        message = notidata.message;

        Button submitBtn = (Button) findViewById(R.id.pr_confirm_finish);

        mAPIService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(time)) {
                    sendNotidata(time, token, message);
                }
            }
        });

        Toast.makeText(this, "time "+time+"\n token : "+token+"\n message :"+message, Toast.LENGTH_LONG).show();
    }
    public void sendNotidata(String time, String token, String message) {
        mAPIService.saveNotidata(time, token, message).enqueue(new Callback<NotiData>() {
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
