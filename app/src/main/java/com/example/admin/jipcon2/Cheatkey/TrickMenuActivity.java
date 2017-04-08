package com.example.admin.jipcon2.Cheatkey;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.admin.jipcon2.R;

/**
 * Created by user on 2017-03-30.
 */

public class TrickMenuActivity extends Fragment {

    public TrickMenuActivity(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.menu_trick, container, false);
        return layout;
    }
}
