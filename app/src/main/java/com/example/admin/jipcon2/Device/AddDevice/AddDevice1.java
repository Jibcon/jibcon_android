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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.admin.jipcon2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-04-15.
 */

public class AddDevice1 extends Fragment {
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
               final String action = intent.getAction();
//            if(action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
//            {
//
//                arr=wifiManager.getScanResults();
//            }else if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION))
//            {
//                getActivity().getApplicationContext().sendBroadcast(new Intent("wifi.ON_NETWORK_STATE_CHANGED"));
//
//            }
            if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
            {
                arr=wifiManager.getScanResults();
                for(int i=0;i<arr.size();i++)
                {
                    wifiNames.add(i,arr.get(i).BSSID);
                    wifiLevels.add(i,new Integer(arr.get(i).level).toString());
                    wifiSeceret.add(i,arr.get(i).capabilities);
                }
            }

        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
            makeDeviceListener=(MakeDeviceListner) context;
    }
    private void wifiSetting()
    {
        wifiManager=(WifiManager)getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.startScan();

        IntentFilter intentFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        getActivity().getApplicationContext().registerReceiver(receiver,intentFilter);
    }

    private void initlayout()
    {
        arr=new ArrayList<>();
        wifiNames=new ArrayList<>();
        wifiLevels=new ArrayList<>();
        wifiSeceret=new ArrayList<>();
        adpater= new WifiListAdpater(getContext());
        adpater.setWifilist(wifiNames);


        listView= (ListView)linearLayout.findViewById(R.id.ListView_adddevice1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                makeDeviceListener.setWifi(wifiNames.get(position));
            }
        });
        listView.setAdapter(adpater);

        nextPage = (Button)linearLayout.findViewById(R.id.Btn_addDevice1);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDeviceListener.NextPage(1);
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
