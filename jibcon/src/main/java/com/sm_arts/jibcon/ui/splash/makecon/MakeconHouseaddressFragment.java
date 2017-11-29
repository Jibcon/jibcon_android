package com.sm_arts.jibcon.ui.splash.makecon;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sm_arts.jibcon.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeconHouseaddressFragment extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    private RelativeLayout mRelativeLayout;
    private GoogleApiClient mGoogleApiClient;
    private String TAG = "MakeconHouseaddressFragment";
    private LinearLayout mUnderLinerLayout;
    private Button mFindLoacationButton;
    @BindView(R.id.splash_makecon_houseaddress_mapview)
    MapView mapView;
    int PLACE_PICKER_REQUEST = 1;
    private HouseInfoListener mHouseInfoListener;

    private MarkerOptions markerOptions;
    private Place place;
    private GoogleMap mGoogleMap;
    private List<Address> address;
    private String currentAddress;
    private String currentLatitute;
    private String currentLongitute;
    private String address2;
    private Location mLocation;
    private Button mButtonOK;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == getActivity().RESULT_OK) {
                place = PlacePicker.getPlace(data, getActivity());

                String toastMsg = String.format("Place: %s", place.getName());
                LatLng currentLocation = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                mGoogleMap.clear();
                markerOptions.position(currentLocation);

                markerOptions.draggable(true);
                markerOptions.title(place.getName().toString());
                markerOptions.snippet(place.getAddress().toString());

                mGoogleMap.addMarker(markerOptions);

                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
                Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        Geocoder geocoder = new Geocoder(getActivity(), Locale.KOREA);
        try {
            address = geocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 3);
            //String currentLocationAddress = address.get(0).getAddressLine(0).toString();
            Address addr = address.get(0);
            address2 = addr.getCountryName() + " " + addr.getPostalCode() + " " + addr.getLocality() + " "
                    + addr.getThoroughfare() + " "
                    + addr.getFeatureName();
            if(!TextUtils.isEmpty(addr.getCountryName()))
                currentAddress+=addr.getCountryName()+" ";
            if(!TextUtils.isEmpty(addr.getPostalCode()))
                currentAddress+=addr.getPostalCode()+" ";
            if(!TextUtils.isEmpty(addr.getLocality()))
                currentAddress+=addr.getLocality()+" ";
            if(!TextUtils.isEmpty(addr.getThoroughfare()))
                currentAddress+=addr.getThoroughfare()+" ";
            if(!TextUtils.isEmpty(addr.getFeatureName()))
                currentAddress+=addr.getFeatureName()+" ";
            currentLatitute = Double.toString(mLocation.getLatitude());
            currentLongitute = Double.toString(mLocation.getLongitude());
            LatLng currentLocation = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
            markerOptions.position(currentLocation);
            markerOptions.title(currentAddress);
            mGoogleMap.addMarker(markerOptions);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        mapView.onStop();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHouseInfoListener = (HouseInfoListener) context;


    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.splashmakecon_makeconhouseaddress_fragment, container, false);
        mUnderLinerLayout = (LinearLayout) mRelativeLayout.findViewById(R.id.makeconhouseaddress_fragemnt_bottom);
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();
        ButterKnife.bind(getActivity());

        initLayout(savedInstanceState);

        return mRelativeLayout;
    }

    private void initLayout(Bundle savedInstanceState) {
        mapView = (MapView) mRelativeLayout.findViewById(R.id.splash_makecon_houseaddress_mapview);
        mFindLoacationButton = (Button) mUnderLinerLayout.findViewById(R.id.btn_start_place_picker);
        mButtonOK = (Button) mUnderLinerLayout.findViewById(R.id.btn_splash_makecon_houseaddress_ok);
        mButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHouseInfoListener.getFragmentNum(1);

            }
        });
        mFindLoacationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(getActivity());

                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (Exception e) {
                    Log.d(TAG, "onCreate: " + "");
                    e.printStackTrace();
                }
            }
        });

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
        mapView.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        markerOptions = new MarkerOptions();

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        mapView.onStart();
    }
}
