package com.example.admin.jipcon2.Device;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.jipcon2.GlobalApplication;
import com.example.admin.jipcon2.R;
import com.example.admin.jipcon2.network.ApiService;
import com.example.admin.jipcon2.network.repo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2017-03-30.
 */

public class DeviceMenuActivity extends Fragment {

    public DeviceMenuActivity(){}


    Button button;
    static myGridView DeviceGridview;
    DeviceMenuAdapter adapter;
    GlobalApplication app;



//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //init();
//    }

    ArrayList<DeviceItem> arr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        app=(GlobalApplication)getActivity().getApplicationContext();


        Log.d("FragmentCheck","DeviceMenuActivity onCreateView");

       //layout.getGravity();
        View root = inflater.inflate(R.layout.menu_divice,container,false);

        button=(Button)root.findViewById(R.id.Btn_getDevices);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = new repo().getService();
                Call<List<DeviceItem>> c = apiService.getDevices("Token "+app.getUserToken());

                try
                {
                    c.enqueue(new Callback<List<DeviceItem>>() {
                        @Override
                        public void onResponse(Call<List<DeviceItem>> call, Response<List<DeviceItem>> response) {

                            arr = new ArrayList<>();
                            for(int i=0;i<response.body().size();i++)
                            {
                                arr.add(response.body().get(i));
                            }
                            app.setDeviceItemArrayList(arr);
                            adapter.setDeviceItems(arr);
                            Toast.makeText(getActivity().getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<List<DeviceItem>> call, Throwable t) {

                            Toast.makeText(getActivity().getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        DeviceGridview = (myGridView)root.findViewById(R.id.ScrollViewDevice);

        DeviceGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),app.getDeviceItemArrayList().get(position).getDeviceName(),Toast.LENGTH_SHORT).show();
                //
            }
        });
        // TODO: 2017-04-06 그리드뷰에 리스너 달기

        adapter = new DeviceMenuAdapter(getActivity().getApplicationContext());
        adapter.setDeviceItems(app.getDeviceItemArrayList());

        DeviceGridview.setAdapter(adapter);

        return root;
    }
}
