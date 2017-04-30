package com.example.admin.jipcon2.Device.AddDevice;

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
import android.widget.Toast;

import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.R;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CHANGE_WIFI_STATE;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDevice1 extends Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    LinearLayout linearLayout;
    Button nextPage;
    ListView listView;
    MakeDeviceListner makeDeviceListener;
    List<ScanResult> arr;
    ArrayList<String> wifiNames;
    ArrayList<String> wifiLevels;
    ArrayList<String> wifiSeceret;
    WifiManager wifiManager;
    WifiListAdpater adpater;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "BroadcastReceiver/onReceive: ");
               final String action = intent.getAction();

            Log.d(TAG, "onReceive: Action RESULTSAVAILABLE is "+action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) +" Action RSSICHANGED is "+action.equals(WifiManager.RSSI_CHANGED_ACTION));
            if(action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) | action.equals(WifiManager.RSSI_CHANGED_ACTION)) // todo rssi_changed 아직 뭔지도 모름
            {
                arr=wifiManager.getScanResults();
                Log.d(TAG, "BroadcastReceiver/onReceive: SCAN_RESULTS_AVAILABLE_ACTION ScanResults is "+arr.toString());
                wifiManager.startScan();
                Log.d(TAG, "BroadcastReceiver/onReceive: restartScan");

                for(int i=0;i<arr.size();i++)
                {
//                    wifiNames.add(i,arr.get(i).BSSID);
//                    wifiLevels.add(i,new Integer(arr.get(i).level).toString());
//                    wifiSeceret.add(i,arr.get(i).capabilities);
                    adpater.setWifilist(arr);
                    adpater.notifyDataSetChanged();
                }
            }else if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION))
            {
                Log.d(TAG, "BroadcastReceiver/onReceive: NETWORK_STATE_CHANGED_ACTION");
                getActivity().getApplicationContext().sendBroadcast(new Intent("wifi.ON_NETWORK_STATE_CHANGED"));

            }
//            if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
//            {
//                arr=wifiManager.getScanResults();
//                Log.d(TAG, "AddDevice1/onReceive: SCAN_RESULTS_AVAILABLE_ACTION ScanResults is "+arr.toString());
//                for(int i=0;i<arr.size();i++)
//                {
//                    wifiNames.add(i,arr.get(i).BSSID);
//                    wifiLevels.add(i,new Integer(arr.get(i).level).toString());
//                    wifiSeceret.add(i,arr.get(i).capabilities);
//                    adpater.setWifilist(wifiNames);
//                    adpater.notifyDataSetChanged();
//                }
//            }

        }
    };

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);
            makeDeviceListener=(MakeDeviceListner) context;
    }
    private void wifiSetting()
    {
        Log.d(TAG, "wifiSetting: ");
        GlobalApplication.getGlobalApplicationContext().chkPermission(ACCESS_WIFI_STATE,getActivity());
        GlobalApplication.getGlobalApplicationContext().chkPermission(ACCESS_COARSE_LOCATION,getActivity());
        GlobalApplication.getGlobalApplicationContext().chkPermission(CHANGE_WIFI_STATE,getActivity());

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.ACTION_REQUEST_SCAN_ALWAYS_AVAILABLE);
        getActivity().getApplicationContext().registerReceiver(receiver,intentFilter);
        wifiManager=(WifiManager)getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifiManager.isWifiEnabled())
        {
            Log.d(TAG, "wifiSetting: Wifi Turn on");
            wifiManager.setWifiEnabled(true);
            Log.d(TAG, "wifiSetting: Wifi Turned On");
//            Toast.makeText(this.getActivity(),"Wifi Turned On",Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, "wifiSetting: Wifi Scan start");
//        Toast.makeText(this.getActivity(),"Wifi Scan started",Toast.LENGTH_SHORT).show();
        wifiManager.startScan();
    }

    private void initlayout()
    {
        Log.d(TAG, "initlayout: ");
        arr=new ArrayList<>();
        wifiNames=new ArrayList<>();
        wifiLevels=new ArrayList<>();
        wifiSeceret=new ArrayList<>();
        adpater= new WifiListAdpater(getContext());
        adpater.setWifilist(new ArrayList<ScanResult>());


        listView= (ListView)linearLayout.findViewById(R.id.ListView_adddevice1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: position "+position);
                makeDeviceListener.setWifi(arr.get(position));
            }
        });
        listView.setAdapter(adpater);

        nextPage = (Button)linearLayout.findViewById(R.id.Btn_addDevice1);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "nextPage/onClick: unregisterReceiver WIFI");
                getActivity().getApplicationContext().unregisterReceiver(receiver);
                makeDeviceListener.NextPage(1);
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        linearLayout = (LinearLayout)inflater.inflate(R.layout.add_device1,container,false);

        wifiSetting();
        initlayout();
        return linearLayout;
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
