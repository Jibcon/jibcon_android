package com.example.admin.jipcon2.Cheatkey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.jipcon2.R;

/**
 * Created by ChanJoo on 2017-04-14.
 */

public class TrickPassive extends android.support.v4.app.Fragment{
    public TrickPassive(){}

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.cheatkey_passive, container, false);

        return layout;
    }
}
