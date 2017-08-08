package com.sm_arts.jibcon.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.setting.SettingActivity;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.ToastHelper;

/**
 * Created by admin on 2017-07-19.
 */

public class SidebarDialog extends Dialog {
    public SidebarDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.ui_sidebardialog_dialog);
        TextView mTextViewSetting = (TextView)findViewById(R.id.txt_sidebardialog_setting);
        TextView mTextViewChangeHome = (TextView)findViewById(R.id.txt_sidebardialog_change_home);
        TextView mTextViewLogout = (TextView)findViewById(R.id.txt_sidebardialog_logout);
        mTextViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context,"설정");
                Intent intent=new Intent(context,SettingActivity.class);
                context.startActivity(intent);
            }
        });
        mTextViewChangeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context,"집 바꾸기");
            }
        });

        mTextViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JibconLoginManager.getInstance().logout(context);
            }
        });
    }
}
