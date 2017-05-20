package com.sm_arts.jibcon.MakeCon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon3Fragment extends android.support.v4.app.Fragment{
    String houselocation;
    ImageButton before;
    Button next;
    //private GoogleMap mMap = null; // Google Maps
    //private float LAT=10,LNG=10; // Google Maps variable

    HouseInfoListener houseInfoListener;
    LinearLayout linearLayout;

    private void initLayout() {
        before= (ImageButton)linearLayout.findViewById(R.id.Btn_MakeCon3_0);
        next =  (Button)linearLayout.findViewById(R.id.Btn_makeCon3_1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        houseInfoListener = (HouseInfoListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout = (LinearLayout) inflater.inflate(R.layout.makecon3,container,false);

        // Google Maps Fragment
        //SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.google_map);

        initLayout();

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseInfoListener.getFragmentNum(-1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseInfoListener.getFragmentNum(1);
            }
        });

        // Google map start
        //mapFragment.getMapAsync(this);

        return linearLayout;
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
