package com.sm_arts.jibcon.ui.makecon;

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
import android.widget.TextView;
import android.widget.Toast;

import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeconHousetypeFragment extends android.support.v4.app.Fragment {

    LinearLayout mLinearLayout;
    Button mNext;
    ImageButton mBefore;
    TextView mBarName;

    HouseInfoListener mHouseInfoListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHouseInfoListener = (HouseInfoListener) context;
    }

    void initLayout()
    {
        // todo 리스트로 바꾸기!(20170430)
        mNext = (Button) getActivity().findViewById(R.id.Btn_makeCon2_1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLinearLayout = (LinearLayout) inflater.inflate(R.layout.makecon_makeconhousetypefragment_fragment,container,false);
      
        // 공간 리스트
        final String[] list_place = {
                "전원 주택", "아파트", "오피스텔", "빌라", "기숙사"
        };

        ListView listView = (ListView) mLinearLayout.findViewById(R.id.list_place);
        mBefore = (ImageButton) mLinearLayout.findViewById(R.id.btn_goback);
        mNext = (Button) mLinearLayout.findViewById(R.id.Btn_makeCon2_1);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                list_place
        );

        mBarName = (TextView) mLinearLayout.findViewById(R.id.bar_name);

        listView.setAdapter(listViewAdapter);

        // 리스트 뷰 클릭 시 houseinfo에 추가
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity().getApplicationContext(),list_place[position],Toast.LENGTH_SHORT).show();
                switch (position) {
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


       // initLayout();

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
//                Intent intent = new Intent(getActivity().getApplicationContext(), MakeconHouseaddressFragment.class);
//                startActivity(intent);
            }
        });

        mBarName.setText("집 유형"); // sorry for hard-coding

        return mLinearLayout;
    }
}
