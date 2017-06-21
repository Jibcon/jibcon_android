package com.sm_arts.jibcon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm_arts.jibcon.device.DeviceMenuFragment;
import com.sm_arts.jibcon.app.cheatkey.TrickMenuActivity;
import com.sm_arts.jibcon.app.conshop.MarketMenuActivity;
import com.sm_arts.jibcon.app.datacontrol.DataControlFragment;
import com.sm_arts.jibcon.app.setting.SettingActivity;
import com.sm_arts.jibcon.app.sidebar.AboutJibconActivity;
import com.sm_arts.jibcon.app.sidebar.ConnectedDevicesActivity;
import com.sm_arts.jibcon.app.sidebar.MyJibconActivity;
import com.sm_arts.jibcon.app.sidebar.UserAuthorityActivity;
import com.sm_arts.jibcon.app.sidebar.WidgetActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    ViewPager mVp;
//    String[] drawer_str = {"about Jibcon", "문의", "알림 설정", "외출", "연결된 디바이스"};//사이드바 임시 메뉴 껍데기
    GlobalApplication mApp;

    Fragment mDevicemenu;
    Fragment mTrickmenu;
    Fragment mMarketmenu;
    Fragment mUsermenu;
    ImageView mUserProfileImage;
    ImageButton mBtn1;
    ImageButton mBtn2;
    ImageButton mBtn3;
    ImageButton mBtn4;

    ImageButton mtoSettingBtn;

    //각 프래그먼트 정보 바뀌면 갱신에서 담아주기
    //속도 너무 느림 ㅠ
    private class pagerAdapter extends FragmentStatePagerAdapter{
        public pagerAdapter(android.support.v4.app.FragmentManager fm){
            super(fm);
        }



        @Override
        public Fragment getItem(int position){
            Log.w("CJ","movePageListener");
                        switch(position){
                case 0:
                    Log.d("FragmentCheck","ToDevice");

                    return mDevicemenu;
                case 1:
                    Log.d("FragmentCheck","ToTrick");
                    mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    mBtn2.setImageResource(R.drawable.ic_link_blue_48dp);
                    mBtn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    return mTrickmenu;
                case 2:
                    Log.d("FragmentCheck","ToMarket");
                    mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    mBtn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    mBtn3.setImageResource(R.drawable.ic_shopping_cart_blue_48dp);
                    mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    return mMarketmenu;
                case 3:
                    Log.d("FragmentCheck","ToUserMenu");
                    mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    mBtn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    mBtn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    mBtn4.setImageResource(R.drawable.ic_pie_chart_blue_48dp);
                    return mUsermenu;
                default:
                    return null;
            }
        }

        @Override
        public int getCount(){
            return 4;
        }
    }
    /* ↑뷰 페이저(액티비티 슬라이드)↑ */

    /* ↓뷰 페이저(액티비티 슬라이드)↓ */
    View.OnClickListener movePageListener = new View.OnClickListener(){


        @Override
        public void onClick(View v){

            int tag = (int) v.getTag();

            switch (tag)
            {
                case 0 :
                    mBtn1.setImageResource(R.drawable.ic_home_blue_48dp);
                    mBtn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    mBtn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    break;
                case 1:
                    mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    mBtn2.setImageResource(R.drawable.ic_link_blue_48dp);
                    mBtn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    break;
                case 2:
                    mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    mBtn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    mBtn3.setImageResource(R.drawable.ic_shopping_cart_blue_48dp);
                    mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    break;
                case 3:
                    mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    mBtn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    mBtn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    mBtn4.setImageResource(R.drawable.ic_pie_chart_blue_48dp);
                    break;
            }

            mVp.setCurrentItem(tag);
        }
    };

    private  void initLayout()
    {
        mDevicemenu = new DeviceMenuFragment();
        mUsermenu = new DataControlFragment();
        mTrickmenu = new TrickMenuActivity();
        mMarketmenu = new MarketMenuActivity();

        mVp = (ViewPager) findViewById(R.id.vp); // activity_main에서 viewpager 객체 생성

         mBtn1 = (ImageButton) findViewById(R.id.btn1); // 기기 버튼
         mBtn2 = (ImageButton) findViewById(R.id.btn2); // 트릭 버튼
         mBtn3 = (ImageButton) findViewById(R.id.btn3); // 마켓 버튼
         mBtn4 = (ImageButton) findViewById(R.id.btn4); // 사용자 버튼

        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: "+position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: "+position);
                switch (position)
                {
                    case 0 :
                        mBtn1.setImageResource(R.drawable.ic_home_blue_48dp);
                        mBtn2.setImageResource(R.drawable.ic_link_gray_48dp);
                        mBtn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                        mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                        break;
                    case 1:
                        mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                        mBtn2.setImageResource(R.drawable.ic_link_blue_48dp);
                        mBtn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                        mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                        break;
                    case 2:
                        mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                        mBtn2.setImageResource(R.drawable.ic_link_gray_48dp);
                        mBtn3.setImageResource(R.drawable.ic_shopping_cart_blue_48dp);
                        mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                        break;
                    case 3:
                        mBtn1.setImageResource(R.drawable.ic_home_gray_48dp);
                        mBtn2.setImageResource(R.drawable.ic_link_gray_48dp);
                        mBtn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                        mBtn4.setImageResource(R.drawable.ic_pie_chart_blue_48dp);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: "+state);
            }
        });
        mVp.setCurrentItem(0); // 첫 뷰페이저로는 기기 목록이 나오도록 설정.

        mBtn1.setTag(0);
        mBtn1.setOnClickListener(movePageListener);
        mBtn2.setTag(1);
        mBtn2.setOnClickListener(movePageListener);
        mBtn3.setTag(2);
        mBtn3.setOnClickListener(movePageListener);
        mBtn4.setTag(3);
        mBtn4.setOnClickListener(movePageListener);

    }
    /* ↑뷰 페이저(액티비티 슬라이드)↑ */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        DrawerLayout.LayoutParams params =(DrawerLayout.LayoutParams)navigationView.getLayoutParams();

        mtoSettingBtn = (ImageButton)findViewById(R.id.Btn_Setting);

        mtoSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });


        params.width=dm.widthPixels;
        navigationView.setLayoutParams(params);
        //네비게이션 뷰 풀화면


        View headerView =navigationView.getHeaderView(0);
        TextView userEmail=(TextView)headerView.findViewById(R.id.Txt_Drawer_UserEmail);
        TextView username = (TextView)headerView.findViewById(R.id.Txt_Drawer_Username);
        mUserProfileImage = (ImageView)headerView.findViewById(R.id.ImgView_Drawer_UserProfile);

        Glide.with(getApplicationContext())
                .load(mApp.getUserProfileImage())
                .into(mUserProfileImage);

        //Log.d("userProfile",mApp.getUserProfileImage().toString());
        username.setText(mApp.getUsername());
        userEmail.setText(mApp.getUserEmail());


        initLayout();
        mBtn1.setImageResource(R.drawable.ic_home_blue_48dp);
        mBtn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
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
        // Inflate the menu; this adds items to the action bar if it is present.
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
//
            Intent intent= new Intent(getApplicationContext(), AboutJibconActivity.class);
            startActivity(intent);


        }// else if (id == R.id.nav_send) {
//
//      }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // for font change
    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
