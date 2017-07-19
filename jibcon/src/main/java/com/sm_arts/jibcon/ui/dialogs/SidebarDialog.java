package com.sm_arts.jibcon.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.setting.SettingActivity;
import com.sm_arts.jibcon.login.JibconLoginManager.JibconLoginManagerImpl;
import com.sm_arts.jibcon.utils.ToastHelper;

/**
 * Created by admin on 2017-07-19.
 */

public class SidebarDialog extends Dialog {
    public SidebarDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.ui_sidebardialog_dialog);
        TextView mtextView1 = (TextView)findViewById(R.id.txt_sidebardialog_setting);
        TextView mtextView2 = (TextView)findViewById(R.id.txt_sidebardialog_change_home);
        TextView mTextView3 = (TextView)findViewById(R.id.txt_sidebardialog_logout);
        mtextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context,"설정");
                Intent intent=new Intent(context,SettingActivity.class);
                context.startActivity(intent);
            }
        });
        mtextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context,"집 바꾸기");
            }
        });

        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JibconLoginManagerImpl jibconLoginManager = new JibconLoginManagerImpl();
                jibconLoginManager.logout(context);
            }
        });
    }
}
