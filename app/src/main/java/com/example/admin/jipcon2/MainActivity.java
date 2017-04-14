package com.example.admin.jipcon2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.jipcon2.Cheatkey.TrickMenuActivity;
import com.example.admin.jipcon2.Conshop.MarketMenuActivity;
import com.example.admin.jipcon2.Device.DeviceMenuActivity;
import com.example.admin.jipcon2.Usermenu.UserMenuActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager vp;
    String[] drawer_str = {"about Jibcon", "문의", "알림 설정", "외출", "연결된 디바이스"};//사이드바 임시 메뉴 껍데기
    GlobalApplication app;
    Fragment devicemenu;
    Fragment trickmenu;
    Fragment marketmenu;
    Fragment usermenu;
    ImageView userProfileImage;
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
                    return devicemenu;
                case 1:
                    Log.d("FragmentCheck","ToTrick");

                    return trickmenu;
                case 2:
                    Log.d("FragmentCheck","ToMarket");

                    return marketmenu;
                case 3:
                    Log.d("FragmentCheck","ToUserMenu");

                    return usermenu;
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
            vp.setCurrentItem(tag);
        }
    };

    private  void initLayout()
    {
        devicemenu = new DeviceMenuActivity();
        usermenu = new UserMenuActivity();
        trickmenu = new TrickMenuActivity();
        marketmenu = new MarketMenuActivity();

        vp = (ViewPager) findViewById(R.id.vp); // activity_main에서 viewpager 객체 생성
        ImageButton btn1 = (ImageButton) findViewById(R.id.btn1); // 기기 버튼
        ImageButton btn2 = (ImageButton) findViewById(R.id.btn2); // 트릭 버튼
        ImageButton btn3 = (ImageButton) findViewById(R.id.btn3); // 마켓 버튼
        ImageButton btn4 = (ImageButton) findViewById(R.id.btn4); // 사용자 버튼

        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0); // 첫 뷰페이저로는 기기 목록이 나오도록 설정.

        btn1.setOnClickListener(movePageListener);
        btn1.setTag(0);
        btn2.setOnClickListener(movePageListener);
        btn2.setTag(1);
        btn3.setOnClickListener(movePageListener);
        btn3.setTag(2);
        btn4.setOnClickListener(movePageListener);
        btn4.setTag(3);

        /* ↑뷰 페이저(액티비티 슬라이드)↑ */
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app=(GlobalApplication)getApplicationContext();
        ActionBarDrawerToggle drawerToggle;
        String[] drawer_str = {"about Jibcon", "문의", "알림 설정", "외출", "연결된 디바이스"};//사이드바 임시 메뉴 껍데기

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //! 여기까지 기본설정
        View headerView =navigationView.getHeaderView(0);
        TextView userEmail=(TextView)headerView.findViewById(R.id.Txt_Drawer_UserEmail);
        TextView username = (TextView)headerView.findViewById(R.id.Txt_Drawer_Username);
        userProfileImage = (ImageView)headerView.findViewById(R.id.ImgView_Drawer_UserProfile);

        Glide.with(getApplicationContext())
                .load(app.getUserProfileImage())
                .into(userProfileImage);

        //Log.d("userProfile",app.getUserProfileImage().toString());
        username.setText(app.getUsername());
        userEmail.setText(app.getUserEmail());

//        try
//        {
//            Bitmap bitmap=Glide.with(MainActivity.this).load(app.getUserProfileImage())
//                    .asBitmap().into(60,60).get();
//            userProfileImage.setImageBitmap(bitmap);
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        initLayout();
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

        if (id == R.id.aboutjipcon) {
            // Handle the camera action
        } else if (id == R.id.asking) {

        } else if (id == R.id.alramsetting) {

        } else if (id == R.id.goout) {

        } else if (id == R.id.connected) {
//
        }// else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
