package com.sm_arts.jibcon.app.makecon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sm_arts.jibcon.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon3Fragment extends android.support.v4.app.Fragment{
    @OnClick(R.id.Btn_MakeCon3_0) void MakeCon3BeforeListener(){
        mHouseInfoListener.getFragmentNum(-1);
    }
    @OnClick(R.id.Btn_makeCon3_1) void MakeCon3NextListener(){
        mHouseInfoListener.getFragmentNum(1);
    }

    ImageButton mBefore;
    Button mNext;
    //private GoogleMap mMap = null; // Google Maps
    //private float LAT=10,LNG=10; // Google Maps variable

    HouseInfoListener mHouseInfoListener;
    LinearLayout mLinearLayout;

    private void initLayout()
    {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHouseInfoListener = (HouseInfoListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLinearLayout = (LinearLayout) inflater.inflate(R.layout.makecon_houseaddress_fragment,container,false);

        // Google Maps Fragment
        //SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.google_map);

        initLayout();
        ButterKnife.bind(this,mLinearLayout);

        // Google map start
        //mapFragment.getMapAsync(this);

        return mLinearLayout;
    }

    /*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(LAT, LNG);

        MarkerOptions marker = new MarkerOptions().position(latLng);

        mMap.addMarker(marker);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
    }
    */
}
