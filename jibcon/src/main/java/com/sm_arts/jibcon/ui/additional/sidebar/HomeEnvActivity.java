package com.sm_arts.jibcon.ui.additional.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.sm_arts.jibcon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeEnvActivity extends AppCompatActivity {

    @BindView(R.id.Btn_makeCon2_1)
    Button Btn_makeCon2_1;


    @BindView(R.id.imageview_sidebar_myjibcon)
    ImageView Back_imageview;
    @BindView(R.id.list_place)
    ListView env_my_jibcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_homeenvactivity_activity);
        ButterKnife.bind(this);

   

        final String[] myjibconDeleteOptionList = getResources().getStringArray(R.array.sidebar_myjibcon_env_array);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myjibconDeleteOptionList);
        env_my_jibcon.setAdapter(adapter);




        Btn_makeCon2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
                startActivity(intent);
            }
        });

        Back_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
                startActivity(intent);
            }
        });
    }
}
