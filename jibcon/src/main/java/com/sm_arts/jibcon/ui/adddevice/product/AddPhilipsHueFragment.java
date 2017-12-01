package com.sm_arts.jibcon.ui.adddevice.product;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionReq;
import com.sm_arts.jibcon.data.models.api.dto.hue.ConnectionRes;
import com.sm_arts.jibcon.ui.adddevice.AddDeviceListner;
import com.sm_arts.jibcon.ui.adddevice.ConvertUtils;
import com.sm_arts.jibcon.ui.adddevice.Hub;
import com.sm_arts.jibcon.ui.adddevice.HueBulb;
import com.sm_arts.jibcon.ui.adddevice.HueControlManager;
import com.sm_arts.jibcon.ui.adddevice.Hue_Internal;
import com.sm_arts.jibcon.ui.adddevice.InternalAddressService;
import com.sm_arts.jibcon.ui.additional.dialogs.HueRegisterDialog;
import com.sm_arts.jibcon.utils.consts.UrlUtils;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.network.GsonUtils;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017-11-30.
 */

public class AddPhilipsHueFragment extends Fragment implements HueDialogListner{
    private AddDeviceListner mMakeDeviceListener;
    private Unbinder mUnbinder;
    private static HashMap<String, LinkedTreeMap<String,Object>> bulbMap;

    private static final String TAG = "AddPhilipsHueFragment";
    private Button mButtonNext;
    private ListView mListView;
    private ArrayList<String> mLightNames;
    private ArrayAdapter<String> mAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMakeDeviceListener = (AddDeviceListner) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMakeDeviceListener = null;
    }

    @Override
    public void setOnDialogClicked() {
        Log.d(TAG, "setOnDialogClicked: ");
        getInternalUsername();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.adddevice_addphilps_activity, container, false);
        mButtonNext = (Button)layout.findViewById(R.id.btn_adddevice_addphilips_ok);
        mListView= (ListView) layout.findViewById(R.id.listview_adddevice_addphilips);
        mLightNames = new ArrayList<>();

        mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mLightNames);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                String hueName = mLightNames.get(position);
                LinkedTreeMap<String,Object> data = bulbMap.get(hueName);

                mMakeDeviceListener.setUserId(JibconLoginManager.getInstance().getUserId());
                mMakeDeviceListener.setDeviceName(hueName);
                mMakeDeviceListener.setData(data);
                mMakeDeviceListener.setDeviceCom("Philips");
            }
        });
        bulbMap = new HashMap<>();
        mUnbinder = ButterKnife.bind(this, layout);
        initLayout();
        getInternalAddress();
        return layout;
    }

    private void initLayout() {

        mButtonNext.setOnClickListener(v -> mMakeDeviceListener.nextPage(this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }



    public void getInternalAddress() {

        InternalAddressService service = RetrofitClients.getInstance().getService(InternalAddressService.class);
        Call<List<Hub>> c = service.getInternalAddress();
        HashMap hashmap = new HashMap();
        hashmap.keySet();
        c.enqueue(new Callback<List<Hub>>() {
            @Override
            public void onResponse(Call<List<Hub>> call, Response<List<Hub>> response) {
                if(!response.isSuccessful())
                    return;
                if(response.body().size() == 0 )
                {
                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(),"Philips Hue와 같은 Wifi를 선택해 주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "onResponse: " + response.body().get(0).internalipaddress);
                UrlUtils.setUrls("internalAddress", response.body().get(0).internalipaddress);
                HueControlManager.internalAddress = "http://";
                HueControlManager.internalAddress +=response.body().get(0).internalipaddress;

                getInternalUsername();

            }

            @Override
            public void onFailure(Call<List<Hub>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    public void getInternalUsername() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(HueControlManager.internalAddress)

                // mentoring
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
                .build();
        Hue_Internal service = client.create(Hue_Internal.class);

        Call<List<ConnectionRes>> c = service.getHueUserName(new ConnectionReq("my_hue_app"));
        c.enqueue(new Callback<List<ConnectionRes>>() {
            @Override
            public void onResponse(Call<List<ConnectionRes>> call, Response<List<ConnectionRes>> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "response error", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().get(0).error != null && !TextUtils.isEmpty(response.body().get(0).error.type)) {
                    Log.d(TAG, "onResponse: " + response.body().get(0).error.toString());

                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "링크 버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
                    HueRegisterDialog hueRegisterDialog = new HueRegisterDialog();
                    hueRegisterDialog.show(getFragmentManager(),"hueDialog");

                } else {
                    Log.d(TAG, "onResponse: " + response.body().get(0).success.username);
                    HueControlManager.internalUsername ="";
                    HueControlManager.internalUsername = response.body().get(0).success.username;

                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "Success" + response.body().get(0).success.username, Toast.LENGTH_SHORT).show();
                    getLights();
                }

            }

            @Override
            public void onFailure(Call<List<ConnectionRes>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                Toast.makeText(GlobalApplication.getGlobalApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    public void getLights() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(HueControlManager.internalAddress)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        Hue_Internal service = client.create(Hue_Internal.class);
        Call<HashMap<String, Object>> c = service.getLights(HueControlManager.internalUsername);
        c.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                HashMap<String, Object> result = response.body();
                Log.d(TAG, "onResponse: ");
                for (String key :
                        result.keySet()) {
                    Object item = result.get(key);
                    Log.d(TAG, "onResponse: item=" + item.toString());
                    HueBulb hueBulb = ConvertUtils.convertHueBulb((LinkedTreeMap<String, Object>) item);
                    hueBulb.ID = key;
                    ((LinkedTreeMap<String, Object>) item).put("internalAddress",HueControlManager.internalAddress);
                    ((LinkedTreeMap<String, Object>) item).put("internalUsername",HueControlManager.internalUsername);
                    LinkedTreeMap<String,Object> data = new LinkedTreeMap<String, Object>();

                    data.put(key,item);
                    bulbMap.put(hueBulb.name, data);
                    mLightNames.add(hueBulb.name);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
