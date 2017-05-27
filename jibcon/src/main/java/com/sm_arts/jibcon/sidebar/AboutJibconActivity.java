package com.sm_arts.jibcon.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.settings.SettingsActivity;
import com.sm_arts.jibcon.settings.alarm.Alarm;
import com.sm_arts.jibcon.settings.personsecure.PersonSecure;
import com.sm_arts.jibcon.settings.usercenter.UserCenter;

public class AboutJibconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_about_jibcon);


    }
}
