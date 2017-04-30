package com.example.admin.jipcon2.MakeCon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.jipcon2.R;
import com.example.admin.jipcon2.Splash.IntroActivity;
import com.facebook.login.LoginManager;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon2 extends android.support.v4.app.Fragment {

    LinearLayout linearLayout;
    Button next;
    Button before;
    HouseInfoListener houseInfoListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        houseInfoListener = (HouseInfoListener)context;
    }

    void initLayout()
    {
        // 리스트로 바꾸기!(20170430)



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        linearLayout = (LinearLayout) inflater.inflate(R.layout.makecon2,container,false);

        // 공간 리스트
        final String[] list_place = {"전원 주택", "아파트", "오피스텔", "빌라", "기숙사"};

        ListView listView   = (ListView)linearLayout.findViewById(R.id.list_place);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                list_place
        );

        listView.setAdapter(listViewAdapter);

        // 리스트 뷰 클릭 시 houseinfo에 추가
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),list_place[position],Toast.LENGTH_SHORT).show();
                switch (position)
                {
                    case 0 :
                        Toast.makeText(getActivity().getApplicationContext(), "전원 주택", Toast.LENGTH_SHORT).show();
                        houseInfoListener.getHouseType("house");
                        break;
                    case 1:
                        Toast.makeText(getActivity().getApplicationContext(), "아파트", Toast.LENGTH_SHORT).show();
                        houseInfoListener.getHouseType("apart");
                        break;
                    case 2:
                        Toast.makeText(getActivity().getApplicationContext(), "오피스텔", Toast.LENGTH_SHORT).show();
                        houseInfoListener.getHouseType("officetel");
                        break;
                    case 3:
                        Toast.makeText(getActivity().getApplicationContext(), "빌라", Toast.LENGTH_SHORT).show();
                        houseInfoListener.getHouseType("villa");
                        break;
                    case 4:
                        Toast.makeText(getActivity().getApplicationContext(), "기숙사", Toast.LENGTH_SHORT).show();
                        houseInfoListener.getHouseType("dorm");
                        break;
                }

            }
        });


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
                Intent intent = new Intent(getActivity().getApplicationContext(), MakeCon3.class);
                startActivity(intent);
            }
        });
        return linearLayout;
    }
}
