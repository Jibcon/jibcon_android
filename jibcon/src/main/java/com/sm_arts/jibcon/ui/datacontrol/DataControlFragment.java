package com.sm_arts.jibcon.ui.datacontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sm_arts.jibcon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chanjoo on 2017-03-30.
 */

public class DataControlFragment extends android.support.v4.app.Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    @BindView(R.id.lv_data_control) ListView mDataControlListView;

    public DataControlFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.datacontrol_datacontrolfragment_fragment, container, false);
        ButterKnife.bind(this, view);

        final String[] datacontrolOptionmenuList = getResources().getStringArray(R.array.datacontrol_option_array);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, datacontrolOptionmenuList);
        mDataControlListView.setAdapter(adapter);

        return view;
    }

}
