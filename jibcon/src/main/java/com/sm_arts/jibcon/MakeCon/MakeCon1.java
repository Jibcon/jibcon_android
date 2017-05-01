package com.sm_arts.jibcon.MakeCon;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.sm_arts.jibcon.R;

/**
 * Created by admin on 2017-04-12.
 */

public class MakeCon1 extends android.support.v4.app.Fragment {
   HouseInfoListener houseInfoListener;
    String housename;
    String username;
    String houseintro;
    Button next;
    ImageButton before;
    LinearLayout linearLayout;
    EditText EditHousename;
    EditText EditUername;
    EditText EditHouseintro;
    Activity activity;
    int fragmentNum;



    private void initLayout()
    {
        next = (Button)linearLayout.findViewById(R.id.Btn_makeCon1_1);
        EditHousename = (EditText)linearLayout.findViewById(R.id.EditTxt_makecon1_1);
        EditUername = (EditText)linearLayout.findViewById(R.id.EditTxt_makecon1_2);
        EditHouseintro=(EditText)linearLayout.findViewById(R.id.EditTxt_makecon1_3);
        fragmentNum = 0;
        before=(ImageButton)linearLayout.findViewById(R.id.Btn_MakeCon1_0);

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            houseInfoListener = (HouseInfoListener) activity;
        }
        catch (Exception e)
        {
            Log.d("onAttach() error","onAttachError!!\n\n\n\n\n");
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        linearLayout = (LinearLayout)inflater.inflate(R.layout.makecon1,container,false);
        initLayout();

        housename=EditHousename.getText().toString();
        houseintro=EditHouseintro.getText().toString();
        username=EditUername.getText().toString();


        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseInfoListener.getFragmentNum(-1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                housename=EditHousename.getText().toString();
                houseintro=EditHouseintro.getText().toString();
                username=EditUername.getText().toString();

                //  ((houseInfoListener)activity).getHouseName(housename);
                houseInfoListener.getHouseIntro(houseintro);
                houseInfoListener.getHouseName(housename);
                houseInfoListener.getUserName(username);
                houseInfoListener.getFragmentNum(1);

//                Intent intent = new Intent(getActivity().getApplicationContext(), MakeCon2.class);
//                startActivity(intent);
            }
        });
        return linearLayout;
    }
}
