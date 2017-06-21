package com.sm_arts.jibcon.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.utils.ToastHelper;

/**
 * Created by admin on 2017-05-27.
 */

public class RoutingDialog extends Dialog {
    public RoutingDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.dialog_routine);
        final TextView mtextView1 = (TextView)findViewById(R.id.Txt_dialog_routine_txt1);
        TextView mtextView2 = (TextView)findViewById(R.id.Txt_dialog_routine_txt2);

        mtextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context,"상세보기");
            }
        });
        mtextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context,"루틴에 추가하기");
            }
        });
    }
}
