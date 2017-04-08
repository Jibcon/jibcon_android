package com.example.admin.jipcon2.Usermenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.jipcon2.R;

/**
 * Created by Chanjoo on 2017-03-30.
 */

public class UserMenuActivity extends android.support.v4.app.Fragment {

    public UserMenuActivity(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ImageView userProfileImage;
    ImageView userProfileChange;
    TextView userProfileEmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.menu_user, container, false);

        // 기능 리스트
        final String[] list_menu = {"사용자 권한 관리", "에너지 사용기록 분석",
                            "사용자 에너지 사용 패턴 분석", "jibcon 제어 기록", "위젯 만들기",
                            "유현호", "안도익", "이승열", "김우진", "이혜진", "이찬주", "왈왈"};

        ListView listview = (ListView) layout.findViewById(R.id.list_menu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                list_menu
        );

        listview.setAdapter(listViewAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),list_menu[position],Toast.LENGTH_SHORT).show();
            }
        });

        initLayout(layout);
        return layout;
    }

    private  void initLayout(View view)
    {

        userProfileImage=(ImageView)view.findViewById(R.id.ImgView_User_Profile);
        userProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017-04-07 이미지  키워보이기
            }
        });
        userProfileChange = (ImageView)view.findViewById(R.id.Btn_User_Profile_Change);
        userProfileChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017-04-07 유저 정보 변경 액티비티 생성
                // TODO: 2017-04-07 유저 이메일 변경
            }
        });
        userProfileEmail= (TextView)view.findViewById(R.id.TxtView_User_Email);


    }


}
