package com.sm_arts.jibcon.app.makecon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon2Fragment extends android.support.v4.app.Fragment {

    LinearLayout mLinearLayout;
    HouseInfoListener mHouseInfoListener;
    @BindView(R.id.list_place) ListView MakeConPlaceListView;
    @OnClick(R.id.Btn_MakeCon2_0) void MakeCon2BeforeListener(){
        mHouseInfoListener.getFragmentNum(-1);}
    @OnClick(R.id.Btn_makeCon2_1) void MakeCon2NextListener(){
        mHouseInfoListener.getFragmentNum(1);}


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHouseInfoListener = (HouseInfoListener)context;
    }

    void initLayout()
    {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mLinearLayout = (LinearLayout) inflater.inflate(R.layout.makecon_housetype_fragment,container,false);
        initLayout();
        ButterKnife.bind(this,mLinearLayout);

        // 공간 리스트
        final String[] list_place = {"전원 주택", "아파트", "오피스텔", "빌라", "기숙사"};

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                list_place
        );

        MakeConPlaceListView.setAdapter(listViewAdapter);
        // 리스트 뷰 클릭 시 houseinfo에 추가
        MakeConPlaceListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity().getApplicationContext(),list_place[position],Toast.LENGTH_SHORT).show();
                switch (position)
                {
                    case 0 :
                        Toast.makeText(getActivity().getApplicationContext(), "전원 주택", Toast.LENGTH_SHORT).show();
                        mHouseInfoListener.getHouseType("house");
                        break;
                    case 1:
                        Toast.makeText(getActivity().getApplicationContext(), "아파트", Toast.LENGTH_SHORT).show();
                        mHouseInfoListener.getHouseType("apart");
                        break;
                    case 2:
                        Toast.makeText(getActivity().getApplicationContext(), "오피스텔", Toast.LENGTH_SHORT).show();
                        mHouseInfoListener.getHouseType("officetel");
                        break;
                    case 3:
                        Toast.makeText(getActivity().getApplicationContext(), "빌라", Toast.LENGTH_SHORT).show();
                        mHouseInfoListener.getHouseType("villa");
                        break;
                    case 4:
                        Toast.makeText(getActivity().getApplicationContext(), "기숙사", Toast.LENGTH_SHORT).show();
                        mHouseInfoListener.getHouseType("dorm");
                        break;
                }

            }
        });

        return mLinearLayout;
    }
}
