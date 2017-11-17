package com.sm_arts.jibcon.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.additional.dialogs.SidebarDialog;
import com.sm_arts.jibcon.ui.additional.sidebar.AboutJibconActivity;
import com.sm_arts.jibcon.ui.additional.sidebar.ConnectedDevicesActivity;
import com.sm_arts.jibcon.ui.additional.sidebar.MyJibconActivity;
import com.sm_arts.jibcon.ui.additional.sidebar.UserAuthorityActivity;
import com.sm_arts.jibcon.ui.additional.sidebar.WidgetActivity;
import com.sm_arts.jibcon.ui.main.cheatkey.CheatkeyMenuFragment;
import com.sm_arts.jibcon.ui.main.conshop.ConshopFragment;
import com.sm_arts.jibcon.ui.main.datacontrol.DataControlFragment;
import com.sm_arts.jibcon.ui.main.devicemenu.fragment.DeviceMenuFragment;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    ViewPager mVp;
    GlobalApplication mApp;
    Fragment mDeviceFragment;
    Fragment mCheatkeyFragment;
    Fragment mConshopFragment;
    Fragment mDataControlFragment;
    ImageView mUserProfileImage;
    FrameLayout mFrameLayout;

    @BindView(R.id.btn1) TextView mDeviceBtn;
    @BindView(R.id.btn2) TextView mCheatkeyBtn;
    @BindView(R.id.btn3) TextView mConshopBtn;
    @BindView(R.id.btn4) TextView mDataControlBtn;


    @BindView(R.id.Sidebar_myjibcon) TextView Sidebar_myjibcon;
    @BindView(R.id.Sidebar_userAuthority) TextView Sidebar_userAuthority;
    @BindView(R.id.Sidebar_connectedDevices) TextView Sidebar_connectedDevices;
    @BindView(R.id.Sidebar_widget) TextView Sidebar_widget;
    @BindView(R.id.Sidebar_aboutJibcon) TextView Sidebar_aboutJibcon;

    ImageButton mtoSettingBtn;

    int fontColor;
    int fontColorPressed;

    /* ↓뷰 페이저(액티비티 슬라이드)↓ */
    View.OnClickListener movePageListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            setSelectedMainMenuBtn(tag);

        }
    };




    private void initLayout() {
        mDeviceFragment = new DeviceMenuFragment();
        mDataControlFragment = new DataControlFragment();
        mCheatkeyFragment = new CheatkeyMenuFragment();
        mConshopFragment = new ConshopFragment();
        mFrameLayout = (FrameLayout) findViewById(R.id.mainFrame);

        setSelectedMainMenuBtn(0);

        mDeviceBtn.setTag(0);
        mDeviceBtn.setOnClickListener(movePageListner);
        mCheatkeyBtn.setTag(1);
        mCheatkeyBtn.setOnClickListener(movePageListner);
        mConshopBtn.setTag(2);
        mConshopBtn.setOnClickListener(movePageListner);
        mDataControlBtn.setTag(3);
        mDataControlBtn.setOnClickListener(movePageListner);

    }

    private void setDefaultMainMenuBtn() {
        mDeviceBtn.setTextColor(fontColor);
        mCheatkeyBtn.setTextColor(fontColor);
        mConshopBtn.setTextColor(fontColor);
        mDataControlBtn.setTextColor(fontColor);
    }

    private void setSelectedMainMenuBtn(int position) {

        switch (position) {
            case 0 :
                setDefaultMainMenuBtn();
                changeFragment(position);
                mDeviceBtn.setTextColor(fontColorPressed);
                break;
            case 1:
                setDefaultMainMenuBtn();
                changeFragment(position);
                mCheatkeyBtn.setTextColor(fontColorPressed);
                break;
            case 2:
                setDefaultMainMenuBtn();
                changeFragment(position);
                mConshopBtn.setTextColor(fontColorPressed);
                break;
            case 3:
                setDefaultMainMenuBtn();
                changeFragment(position);
                mDataControlBtn.setTextColor(fontColorPressed);
                break;
        }
    }

    private void changeFragment(int position) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new DeviceMenuFragment();
                supportFragmentManager.popBackStack();
                fragmentTransaction.replace(R.id.mainFrame, fragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragment = new CheatkeyMenuFragment();

                supportFragmentManager.popBackStack();
                fragmentTransaction.replace(R.id.mainFrame, fragment);
                fragmentTransaction.commit();

                break;
            case 2:
                fragment = new ConshopFragment();
                supportFragmentManager.popBackStack();
                fragmentTransaction.replace(R.id.mainFrame, fragment);
                fragmentTransaction.commit();

                break;
            case 3:
                fragment = new DataControlFragment();
                supportFragmentManager.popBackStack();
                fragmentTransaction.replace(R.id.mainFrame, fragment);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_mainactivity_activity);
        ButterKnife.bind(this);

        fontColor = getResources().getColor(R.color.mainactivity_font_color);
        fontColorPressed = getResources().getColor(R.color.mainactivity_font_color_pressed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        //toolbar search tab  : res-> menu
        //toolbar hamburder menu :  style->ToolbarColoredBackArrow

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mApp = (GlobalApplication)getApplicationContext();

        ActionBarDrawerToggle drawerToggle;

        String[] drawer_str = {"about Jibcon", "문의", "알림 설정", "외출", "연결된 디바이스"};// 사이드바 임시 메뉴 껍데기

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //네비게이션 뷰 풀화면
       // DisplayMetrics dm = new DisplayMetrics();
      //  getWindowManager().getDefaultDisplay().getMetrics(dm);
      //  DrawerLayout.LayoutParams params =(DrawerLayout.LayoutParams)navigationView.getLayoutParams();

        mtoSettingBtn = (ImageButton) findViewById(R.id.Btn_Setting);

        mtoSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SidebarDialog sidebarDialog = new SidebarDialog(MainActivity.this);
                sidebarDialog.show();

            }
        });

        Sidebar_myjibcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
                startActivity(intent);
            }
        });

        Sidebar_userAuthority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), UserAuthorityActivity.class);
                startActivity(intent);
            }
        });

        Sidebar_connectedDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ConnectedDevicesActivity.class);
                startActivity(intent);
            }
        });

        Sidebar_widget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), WidgetActivity.class);
                startActivity(intent);
            }
        });

        Sidebar_aboutJibcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), AboutJibconActivity.class);
                startActivity(intent);
            }
        });

  //      params.width=dm.widthPixels;
     //   navigationView.setLayoutParams(params);
        //네비게이션 뷰 풀화면

        View headerView =navigationView.getHeaderView(0);
        TextView userEmail = (TextView) headerView.findViewById(R.id.Txt_Drawer_UserEmail);
        TextView username = (TextView) headerView.findViewById(R.id.Txt_Drawer_Username);
        mUserProfileImage = (ImageView) headerView.findViewById(R.id.ImgView_Drawer_UserProfile);

        Glide.with(getApplicationContext())
                .load(JibconLoginManager.getInstance()
                        .getUserProfileImageUrl())
                .into(mUserProfileImage);

        //Log.d("userProfile",mApp.getUserProfileImageUrl().toString());
        username.setText(JibconLoginManager.getInstance().getUserName());
        userEmail.setText(JibconLoginManager.getInstance().getUserEmail());

        initLayout();

        startTapTargetView();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds mItems to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Sidebar_myjibcon) {
            Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.Sidebar_userAuthority) {
            Intent intent= new Intent(getApplicationContext(), UserAuthorityActivity.class);
            startActivity(intent);
        } else if (id == R.id.Sidebar_connectedDevices) {
            Intent intent= new Intent(getApplicationContext(), ConnectedDevicesActivity.class);
            startActivity(intent);
        } else if (id == R.id.Sidebar_widget) {
            Intent intent= new Intent(getApplicationContext(), WidgetActivity.class);
            startActivity(intent);
        } else if (id == R.id.Sidebar_aboutJibcon) {
            Intent intent= new Intent(getApplicationContext(), AboutJibconActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setTapTargetView() {
        TapTargetSequence mTapTargetSequence = new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(mDeviceBtn, "디바이스 메뉴", "등록된 디바이스들을 한눈에!\n하나의 창에서 조작해보세요.")
                                .cancelable(false)
                                .outerCircleColor(R.color.deep_blue)
                                .targetRadius(50),
                        TapTarget.forView(mCheatkeyBtn, "치트키 메뉴", "등록된 디바이스들로\n액티브, 패시브 치트키를\n만들어보세요.")
                                .cancelable(false)
                                .outerCircleColor(R.color.deep_blue)
                                .targetRadius(50),
                        TapTarget.forView(mConshopBtn, "콘샾 메뉴", "혁신적 제품들을 만나보세요.")
                                .cancelable(false)
                                .outerCircleColor(R.color.deep_blue)
                                .targetRadius(50),
                        TapTarget.forView(mDataControlBtn, "데이터 메뉴", "낭비되는 전기세부터\n나에게 맞는 온도까지!\n데이터로 알아보세요.")
                                .outerCircleColor(R.color.deep_blue)
                                .targetRadius(80))
                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        setSelectedMainMenuBtn(0);
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                    }
                });

        mTapTargetSequence.start();
    }

    public void startTapTargetView() {
        try{
            SharedPreferences mPref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);

            Boolean bFirst = mPref.getBoolean("isFirst", false);
            if(bFirst == false)
            {
                Log.d("CJ : ", "It's first time this App started.");
                SharedPreferences.Editor editor = mPref.edit();
                editor.putBoolean("isFirst", true);
                editor.commit();

                //최초 실행시 필요한 코드를 여기에 작성
                setTapTargetView(); // 초기 도움말 View
            }
            if(bFirst == true)
            {
                Log.d("CJ : ", "It isn't first time this App started.");
            }

        } catch(Exception e) {

        }
    }
}
