package com.sm_arts.jibcon.ui.splash.makecon;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeconHouseaddressFragment extends android.support.v4.app.Fragment implements GoogleApiClient.OnConnectionFailedListener{
    ImageButton mBefore;
    Button mNext;
    Button mPlacePickerBtn;
    TextView mBarName;
    TextView mPlaceName;
    TextView mPlaceAddress;
    HouseInfoListener mHouseInfoListener;
    LinearLayout mLinearLayout;
    LinearLayout mUnderLinerLayout;

    private GoogleApiClient mGoogleApiClient;
    private String TAG ="MakeconHouseaddressFragment";
    
    int PLACE_PICKER_REQUEST =1;
    
    
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        
    }

    private void initLayout() {
        mBefore= (ImageButton) mLinearLayout.findViewById(R.id.btn_goback);
        mNext =  (Button) mUnderLinerLayout.findViewById(R.id.Btn_makeCon3_1);
        mBarName = (TextView) mLinearLayout.findViewById(R.id.bar_name);
        mPlaceAddress = (TextView) mLinearLayout.findViewById(R.id.txt_place_address);
        mPlaceName = (TextView)mLinearLayout.findViewById(R.id.txt_place_name);
        mPlacePickerBtn = (Button) mUnderLinerLayout.findViewById(R.id.btn_start_place_picker);


        mBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHouseInfoListener.getFragmentNum(-1);
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHouseInfoListener.getFragmentNum(1);
            }
        });

        mPlacePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                permisionCheck();
                makePlacePicker();
            }
        });

        mBarName.setText("집 주소"); // sorry for hard-coding
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHouseInfoListener = (HouseInfoListener) context;
        Log.d(TAG, "onAttach: ");


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PLACE_PICKER_REQUEST)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Place place = PlacePicker.getPlace(data, getContext());
                Log.d(TAG, "onActivityResult: address"+place.getAddress());
                Log.d(TAG, "onActivityResult: name"+place.getName());
                Log.d(TAG, "onActivityResult: getLatLng"+place.getLatLng());


                Log.d(TAG, "onActivityResult: "+place.getAttributions());

                Log.d(TAG, "onActivityResult: "+place.getViewport());
                Log.d(TAG, "onActivityResult: "+place.getLocale());
                mPlaceAddress.setText(place.getAddress());
                mPlaceName.setText(place.getName());



            }
        }

    }
    private void permisionCheck() {

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},15);
        }

    }
    private void makePlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(getActivity()),PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /* at the end of fragment's end, googleApiClient must be stoped and diesconnected -EUIJOON~~*/
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLinearLayout = (LinearLayout) inflater.inflate(R.layout.splashmakecon_makeconhouseaddress_fragment,container,false);
        mUnderLinerLayout = (LinearLayout) mLinearLayout.findViewById(R.id.makeconhouseaddress_fragemnt_bottom);
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(),this)
                .build();

        Log.d(TAG, "onCreateView: ");
        initLayout();


        return mLinearLayout;
    }
}
