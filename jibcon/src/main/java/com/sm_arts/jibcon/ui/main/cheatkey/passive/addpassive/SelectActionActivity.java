package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sm_arts.jibcon.R;

public class SelectActionActivity extends AppCompatActivity {
    private static final String TAG = "SelectActionActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.main_cheatkey_addpassive_selectaction_activity);

    }
}
