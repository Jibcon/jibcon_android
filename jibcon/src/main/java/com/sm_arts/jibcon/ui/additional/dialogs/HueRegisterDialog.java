package com.sm_arts.jibcon.ui.additional.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.widget.Button;

import com.sm_arts.jibcon.ui.adddevice.product.AddPhilipsHueFragment;
import com.sm_arts.jibcon.ui.adddevice.product.HueDialogListner;

import java.util.List;

/**
 * Created by admin on 2017-12-01.
 */

public class HueRegisterDialog extends DialogFragment {

    Button mButtonOk;
    HueDialogListner mHueDialogListner;
    List<Fragment> fragments;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog mAlertDialog;
        mAlertDialog = new AlertDialog.Builder(getActivity())
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        fragments = manager.getFragments();
                        for(int i=0;i<fragments.size();i++)
                        {
                            if(TextUtils.equals(fragments.get(i).getClass().getName(),AddPhilipsHueFragment.class.getName()))
                            {
                                mHueDialogListner = (HueDialogListner) fragments.get(i);
                                break;
                            }
                        }
                        mHueDialogListner.setOnDialogClicked();
                    }
                })
                .setTitle("링크 버튼을 눌러주세요")
                .create();
        return mAlertDialog;
    }
}
