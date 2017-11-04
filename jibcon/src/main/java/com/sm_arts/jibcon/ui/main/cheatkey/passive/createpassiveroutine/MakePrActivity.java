package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities.PrMakeNew;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by woojinkim on 2017. 11. 4..
 */

public class MakePrActivity extends BaseActivity {

    private static final String TAG = "tagitis";

    String time = "default";
    String token = FirebaseInstanceId.getInstance().getToken();
    String message = "default";

    @OnClick(R.id.plus)
    void addone() {
        Intent intent = new Intent(this, PrMakeNew.class);
        NotiData prinfo = new NotiData(time, token, message);
        intent.putExtra("come", prinfo);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_makenew);

        Log.d("token", token);
        ButterKnife.bind(this);
    }
}