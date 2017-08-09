package com.sm_arts.jibcon.ui.adddevice.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.adddevice.AddDeviceListner;
import com.sm_arts.jibcon.ui.adddevice.wifi.adapter.WifiListAdpater;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CHANGE_WIFI_STATE;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDeviceWifiFragment extends Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    private LinearLayout mLinearLayout;
    private Button mNextPage;
    private ListView mListView;
    private AddDeviceListner mMakeDeviceListener;
    private List<ScanResult> mArr;
    private ArrayList<String> mWifiNames;
    private ArrayList<String> mWifiLevels;
    private ArrayList<String> mWifiSeceret;
    private WifiManager mWifiManager;
    private WifiListAdpater mAdapter;

//    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d(TAG, "BroadcastReceiver/onReceive: ");
//               final String action = intent.getAction();
//
//            Log.d(TAG, "onReceive: Action RESULTSAVAILABLE is "+action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) +" Action RSSICHANGED is "+action.equals(WifiManager.RSSI_CHANGED_ACTION));
//            if(action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) | action.equals(WifiManager.RSSI_CHANGED_ACTION)) { // todo rssi_changed 아직 뭔지도 모름
//                mArr=mWifiManager.getScanResults();
//                Log.d(TAG, "BroadcastReceiver/onReceive: SCAN_RESULTS_AVAILABLE_ACTION ScanResults is "+mArr.toString());
//                mWifiManager.startScan();
//                Log.d(TAG, "BroadcastReceiver/onReceive: restartScan");
//
//                for(int i=0;i<mArr.size();i++) {
////                    mWifiNames.add(i,mArr.get(i).BSSID);
////                    mWifiLevels.add(i,new Integer(mArr.get(i).level).toString());
////                    mWifiSeceret.add(i,mArr.get(i).capabilities);
//
//                    mAdapter.setWifilist(mArr);
//                    mAdapter.notifyDataSetChanged();
//                }
//            } else if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
//                Log.d(TAG, "BroadcastReceiver/onReceive: NETWORK_STATE_CHANGED_ACTION");
//                getActivity().getApplicationContext().sendBroadcast(new Intent("wifi.ON_NETWORK_STATE_CHANGED"));
//            }
////            if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
////            {
////                mArr=mWifiManager.getScanResults();
////                Log.d(TAG, "AddDeviceWifiFragment/onReceive: SCAN_RESULTS_AVAILABLE_ACTION ScanResults is "+mArr.toString());
////                for(int i=0;i<mArr.size();i++)
////                {
////                    mWifiNames.add(i,mArr.get(i).BSSID);
////                    mWifiLevels.add(i,new Integer(mArr.get(i).level).toString());
////                    mWifiSeceret.add(i,mArr.get(i).capabilities);
////                    mAdapter.setWifilist(mWifiNames);
////                    mAdapter.notifyDataSetChanged();
////                }
////            }
//
//        }
//    };

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);
        mMakeDeviceListener = (AddDeviceListner) context;
    }

//    private void wifiSetting() {
//        Log.d(TAG, "wifiSetting: ");
//        GlobalApplication.getGlobalApplicationContext().chkPermission(ACCESS_WIFI_STATE,getActivity());
//        GlobalApplication.getGlobalApplicationContext().chkPermission(ACCESS_COARSE_LOCATION,getActivity());
//        GlobalApplication.getGlobalApplicationContext().chkPermission(CHANGE_WIFI_STATE,getActivity());
//
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
//        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
//        intentFilter.addAction(WifiManager.ACTION_REQUEST_SCAN_ALWAYS_AVAILABLE);
//        getActivity().getApplicationContext().registerReceiver(mReceiver,intentFilter);
//        mWifiManager=(WifiManager)getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//        if(!mWifiManager.isWifiEnabled()) {
//            Log.d(TAG, "wifiSetting: Wifi Turn on");
//            mWifiManager.setWifiEnabled(true);
//            Log.d(TAG, "wifiSetting: Wifi Turned On");
////            Toast.makeText(this.getActivity(),"Wifi Turned On",Toast.LENGTH_SHORT).show();
//        }
//        Log.d(TAG, "wifiSetting: Wifi Scan start");
////        Toast.makeText(this.getActivity(),"Wifi Scan started",Toast.LENGTH_SHORT).show();
//        mWifiManager.startScan();
//    }

    private void initlayout() {
        Log.d(TAG, "initlayout: ");
        mArr = new ArrayList<>();
        mWifiNames = new ArrayList<>();
        mWifiLevels = new ArrayList<>();
        mWifiSeceret = new ArrayList<>();
        mAdapter = new WifiListAdpater(getContext());
        mAdapter.setWifilist(new ArrayList<ScanResult>());


        mListView = (ListView) mLinearLayout.findViewById(R.id.ListView_adddevice1);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: position "+position);
//                mMakeDeviceListener.setWifi(mArr.get(position));
            }
        });
        mListView.setAdapter(mAdapter);

        mNextPage = (Button)mLinearLayout.findViewById(R.id.Btn_addDevice1);
        mNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "mNextPage/onClick: unregisterReceiver WIFI");
//                getActivity().getApplicationContext().unregisterReceiver(mReceiver);
                mMakeDeviceListener.nextPage(1);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mLinearLayout = (LinearLayout)inflater.inflate(R.layout.device_adddevicewififragment_fragment,container,false);

//        wifiSetting();
        initlayout();
        return mLinearLayout;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
