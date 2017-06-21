package com.sm_arts.jibcon.app.datacontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.utils.ToastHelper;

/**
 * Created by Chanjoo on 2017-03-30.
 */

public class DataControlFragment extends android.support.v4.app.Fragment {
    private final String TAG = "jibcon/" + getClass().getSimpleName();

    GlobalApplication mApp;
    ListView mDataControlListView;
    static final String[] mDataControlList = {"기기별 전기 사용량","집콘 사용 기록","기간별 전기 사용량","실내 온습도 및 미세먼지 측정"};

    public DataControlFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp=(GlobalApplication) getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.datacontrol, container, false);


        // 기능 리스트

        mDataControlListView = (ListView) layout.findViewById(R.id.Lv_data_control);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mDataControlList
        );

        //Fragment 에선 첫번째 인자가 this가 아니라 getActivity이다//
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mDataControlList);

        mDataControlListView.setAdapter(adapter);

        mDataControlListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String settingClickedItem = (String) mDataControlListView.getItemAtPosition(position);
                //Toast도 마찬가지로 fragment에서 띄우려면 getactivity() //
                ToastHelper.toast(getActivity(),settingClickedItem);

//                    Log.d(TAG, "onItemClick: "+list_menu[position]);
//                switch (position)
//                {
//                    case 6 :
//                        SharedPreferences pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = pref.edit();
//                        editor.clear();
//                        editor.commit();
//                        //sharedpreference에 저장된 값 지우기
//                        //페이스북 로그아웃
//                        LoginManager.getInstance().logOut();
//
//                        //카카오 로그아웃
//                        UserManagement.requestLogout(new LogoutResponseCallback() {
//                            @Override
//                            public void onCompleteLogout() {
//
//                            }
//                        });
//
//
//                        getActivity().finish();
//
//
//
//                        Intent intent = new Intent(getActivity().getApplicationContext(), IntroActivity.class);
//                        startActivity(intent);
//                        getActivity().finish();
//
//                        break;
//                    case 5:
//                        //새 집콘 만들기
//                        Intent intent1 = new Intent(getActivity().getApplicationContext(), MakeConStartActivity.class);
//                        startActivity(intent1);
//                        getActivity().finish();
//                        break;
//                }

            }
        });

//        initLayout(layout);
        return layout;
    }
//
//    private  void initLayout(View view)
//    {
//        username=(TextView)view.findViewById(R.id.user_name);
//        userProfileImage=(ImageView)view.findViewById(R.id.ImgView_User_Profile);
//        userProfileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO: 2017-04-07 이미지  키워보이기
//            }
//        });
//        userProfileChange = (ImageView)view.findViewById(R.id.Btn_User_Profile_Change);
//        userProfileChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO: 2017-04-07 유저 정보 변경 액티비티 생성
//                // TODO: 2017-04-07 유저 이메일 변경
//            }
//        });
//        userProfileEmail= (TextView)view.findViewById(R.id.TxtView_User_Email);
//
//        username.setText(app.getUsername());
//        userProfileEmail.setText(app.getUserEmail());
////        try
////        {
////            Bitmap bitmap= Glide.with(getContext()).load(app.getUserProfileImage())
////                    .asBitmap().into(60,60).get();
////            userProfileImage.setImageBitmap(bitmap);
////        }catch (Exception e)
////        {
////            Log.d("Profile change","Username error");
////            e.printStackTrace();
////        }
//        Glide.with(getActivity().getApplicationContext())
//                .load(app.getUserProfileImage())
//                .into(userProfileImage);
//
//
//
//    }
//

}
