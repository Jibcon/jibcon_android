package com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive.action;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.ActionWeatherData;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.addpassive.IntentCodeEnum;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectActionWeatherActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {
    private int PLACE_PICKER_REQUEST = 1;
    private Activity activity;
    private Fragment mFragment;
    private Location mLocation;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SelectActionWeatherActi";
    List<Address> address;
    String address2;
    GoogleMap mGoogleMap;
    @BindView(R.id.cheatkey_addpassive_wehatermap_mapview)
    MapView mapView;

    @BindView(R.id.tv_cheatkey_addpassive_weatheraction)
    TextView mTextView;
    @OnClick(R.id.btn_cheatkey_addpassive_start_place_select)
    void setOnClick() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = builder.build(this);

            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (Exception e) {
            Log.d(TAG, "onCreate: " + "GooglePlayServicesRepairableException");
            e.printStackTrace();
        }
    }
    MarkerOptions markerOptions;
    Place place;
    String currentAddress ="";
    String currentLatitute ="";
    String currentLongitute= "";

    @OnClick(R.id.btn_selectweather_ok)
    void setOnWeatherBtnOk() {

        Intent intent = new Intent();
        ActionWeatherData actionWeatherData = new ActionWeatherData();


        actionWeatherData.lat = currentLatitute;
        actionWeatherData.lon = currentLongitute;

        intent.putExtra("SELECTED_ACTION_DATA", actionWeatherData);
        intent.putExtra("SELECTED_ACTION_MENT", currentAddress+" 의 날씨를 알려줘");
        // Toast.makeText(getApplicationContext(),"위도 "+actionWeatherData.lat + " 경도 "+actionWeatherData.lon+" 의 날씨를 알려줘",Toast.LENGTH_SHORT).show();
        setResult(IntentCodeEnum.WEATHER_RESULT, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_cheatkey_addpassive_selectweather_activity);
        ButterKnife.bind(this);

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
        mapView.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        markerOptions = new MarkerOptions();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(data, this);

                String toastMsg = String.format("Place: %s", place.getName());
                LatLng currentLocation = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                mGoogleMap.clear();
                markerOptions.position(currentLocation);

                markerOptions.draggable(true);
                markerOptions.title(place.getName().toString());
                markerOptions.snippet(place.getAddress().toString());
                currentAddress =place.getAddress().toString();
                currentLatitute = Double.toString(place.getLatLng().latitude);
                currentLongitute = Double.toString(place.getLatLng().longitude);
                mTextView.setText(currentAddress+" 의 날씨를 알려줘");
                mGoogleMap.addMarker(markerOptions);

                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
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
            mTextView.setText(currentAddress);
            mGoogleMap.addMarker(markerOptions);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        mapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: " + googleMap);
        mGoogleMap = googleMap;
//        LatLng currentLocation = new LatLng(37.56, 126.97);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(currentLocation);
//        markerOptions.title("서울");
//        markerOptions.snippet("수도");
//        googleMap.addMarker(markerOptions);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

}
