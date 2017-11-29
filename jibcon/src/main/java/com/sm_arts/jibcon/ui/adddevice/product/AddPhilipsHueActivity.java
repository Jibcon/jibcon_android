package com.sm_arts.jibcon.ui.adddevice.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.adddevice.HueRegisterManager;

public class AddPhilipsHueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddevice_addphilps_activity);
        getInternalAddress();
        getInternalUsername();
        getLights();
        updateToAdapter();

    }

    private void updateToAdapter() {
    }

    private void getLights() {
        HueRegisterManager.getLights();
    }

    private void getInternalUsername() {
        HueRegisterManager.getInternalUsername();
    }

    private void getInternalAddress() {
        HueRegisterManager.getInternalAddress();
    }


}
