package com.sm_arts.jibcon.app.datacontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sm_arts.jibcon.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chanjoo on 2017-03-30.
 */

public class DataControlFragment extends android.support.v4.app.Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    @BindView(R.id.lv_data_control) ListView mDataControlListView;
    @BindString(R.string.datacontrol_menu_1) String menu1;
    @BindString(R.string.datacontrol_menu_2) String menu2;
    @BindString(R.string.datacontrol_menu_3) String menu3;
    @BindString(R.string.datacontrol_menu_4) String menu4;

    public DataControlFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.datacontrol_main_fragment, container, false);
        ButterKnife.bind(this, view);

        String[] mDataControlList = {
                menu1, menu2, menu3, menu4
        };


        //Fragment 에선 첫번째 인자가 this가 아니라 getActivity이다//
        ArrayAdapter adapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mDataControlList
        );

        mDataControlListView.setAdapter(adapter);

        return view;
    }

}
