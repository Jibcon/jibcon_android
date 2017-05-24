package com.sm_arts.jibcon.Usermenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.MakeCon.MakeConStartActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.Splash.IntroActivity;

/**
 * Created by Chanjoo on 2017-03-30.
 */

public class UserMenuActivity extends android.support.v4.app.Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    GlobalApplication app;

    public UserMenuActivity(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app=(GlobalApplication) getActivity().getApplicationContext();
    }
    TextView username;
    ImageView userProfileImage;
    ImageView userProfileChange;
    TextView userProfileEmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.menu_user, container, false);


        // 기능 리스트
        final String[] list_menu = {"사용자 권한 관리", "에너지 사용기록 분석",
                            "사용자 에너지 사용 패턴 분석", "jibcon 제어 기록", "위젯 만들기",
                           "새 집콘 만들기","로그아웃"};

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
                Log.d(TAG, "onItemClick: "+list_menu[position]);

//                Toast.makeText(getActivity().getApplicationContext(),list_menu[position],Toast.LENGTH_SHORT).show();
                switch (position)
                {
                    case 6 :
                        SharedPreferences pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.clear();
                        editor.commit();
                        //sharedpreference에 저장된 값 지우기
                        //페이스북 로그아웃
                        LoginManager.getInstance().logOut();

                        //카카오 로그아웃
                        UserManagement.requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {

                            }
                        });


                        getActivity().finish();



                        Intent intent = new Intent(getActivity().getApplicationContext(), IntroActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                        break;
                    case 5:
                        //새 집콘 만들기
                        Intent intent1 = new Intent(getActivity().getApplicationContext(), MakeConStartActivity.class);
                        startActivity(intent1);
                        getActivity().finish();
                        break;
                }

            }
        });

        initLayout(layout);
        return layout;
    }

    private  void initLayout(View view)
    {
        username=(TextView)view.findViewById(R.id.user_name);
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

        username.setText(app.getUsername());
        userProfileEmail.setText(app.getUserEmail());
//        try
//        {
//            Bitmap bitmap= Glide.with(getContext()).load(app.getUserProfileImage())
//                    .asBitmap().into(60,60).get();
//            userProfileImage.setImageBitmap(bitmap);
//        }catch (Exception e)
//        {
//            Log.d("Profile change","Username error");
//            e.printStackTrace();
//        }
        Glide.with(getActivity().getApplicationContext())
                .load(app.getUserProfileImage())
                .into(userProfileImage);



    }


}
