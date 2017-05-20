package com.sm_arts.jibcon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm_arts.jibcon.Cheatkey.TrickMenuActivity;
import com.sm_arts.jibcon.Conshop.MarketMenuActivity;
import com.sm_arts.jibcon.Device.DeviceMenuActivity;
import com.sm_arts.jibcon.Settings.SettingActivity;
import com.sm_arts.jibcon.SidebarMenu.AboutJibcon;
import com.sm_arts.jibcon.SidebarMenu.ConnectedDevices;
import com.sm_arts.jibcon.SidebarMenu.MyJibcon;
import com.sm_arts.jibcon.SidebarMenu.UserAuthority;
import com.sm_arts.jibcon.SidebarMenu.Widget;
import com.sm_arts.jibcon.Usermenu.UserMenuActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    ViewPager vp;
    String[] drawer_str = {"about Jibcon", "문의", "알림 설정", "외출", "연결된 디바이스"};//사이드바 임시 메뉴 껍데기
    GlobalApplication app;

    Fragment devicemenu;
    Fragment trickmenu;
    Fragment marketmenu;
    Fragment usermenu;
    ImageView userProfileImage;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;

    Button mtoSettingBtn;

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
            //활성화버튼
            BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_home_blue_48dp);
            Bitmap bitmap1 = drawable1.getBitmap();
            BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_link_blue_48dp);
            Bitmap bitmap2 = drawable2.getBitmap();
            BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_shopping_cart_blue_48dp);
            Bitmap bitmap3 = drawable3.getBitmap();
            BitmapDrawable drawable4 = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_pie_chart_blue_48dp);
            Bitmap bitmap4 = drawable4.getBitmap();

            //비활성화 버튼
            BitmapDrawable drawable1_1 = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_home_gray_48dp);
            Bitmap bitmap1_1 = drawable1_1.getBitmap();
            BitmapDrawable drawable2_1 = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_link_gray_48dp);
            Bitmap bitmap2_1 = drawable2_1.getBitmap();
            BitmapDrawable drawable3_1 = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_shopping_cart_gray_48dp);
            Bitmap bitmap3_1 = drawable3_1.getBitmap();
            BitmapDrawable drawable4_1 = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_pie_chart_gray_48dp);
            Bitmap bitmap4_1 = drawable4_1.getBitmap();

            int tag = (int) v.getTag();

            switch (tag)
            {
                case 0 :
                    btn1.setImageBitmap(bitmap1);
                    btn2.setImageBitmap(bitmap2_1);
                    btn3.setImageBitmap(bitmap3_1);
                    btn4.setImageBitmap(bitmap4_1);
                    break;
                case 1:
                    btn2.setImageBitmap(bitmap2);
                    btn1.setImageBitmap(bitmap1_1);
                    btn3.setImageBitmap(bitmap3_1);
                    btn4.setImageBitmap(bitmap4_1);
                    break;
                case 2:
                    btn3.setImageBitmap(bitmap3);
                    btn2.setImageBitmap(bitmap2_1);
                    btn1.setImageBitmap(bitmap1_1);
                    btn4.setImageBitmap(bitmap4_1);
                    break;
                case 3:
                    btn4.setImageBitmap(bitmap4);
                    btn2.setImageBitmap(bitmap2_1);
                    btn3.setImageBitmap(bitmap3_1);
                    btn1.setImageBitmap(bitmap1_1);
                    break;
            }

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

         btn1 = (ImageButton) findViewById(R.id.btn1); // 기기 버튼
         btn2 = (ImageButton) findViewById(R.id.btn2); // 트릭 버튼
         btn3 = (ImageButton) findViewById(R.id.btn3); // 마켓 버튼
         btn4 = (ImageButton) findViewById(R.id.btn4); // 사용자 버튼

        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0); // 첫 뷰페이저로는 기기 목록이 나오도록 설정.

        btn1.setTag(0);
        btn1.setOnClickListener(movePageListener);
        btn2.setTag(1);
        btn2.setOnClickListener(movePageListener);
        btn3.setTag(2);
        btn3.setOnClickListener(movePageListener);
        btn4.setTag(3);
        btn4.setOnClickListener(movePageListener);

    }
    /* ↑뷰 페이저(액티비티 슬라이드)↑ */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        app = (GlobalApplication)getApplicationContext();

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

        mtoSettingBtn = (Button)findViewById(R.id.Btn_Setting);

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
        userProfileImage = (ImageView)headerView.findViewById(R.id.ImgView_Drawer_UserProfile);

        Glide.with(getApplicationContext())
                .load(app.getUserProfileImage())
                .into(userProfileImage);

        //Log.d("userProfile",app.getUserProfileImage().toString());
        username.setText(app.getUsername());
        userEmail.setText(app.getUserEmail());


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

        if (id == R.id.Sidebar_myjibcon) {

            Intent intent= new Intent(getApplicationContext(), MyJibcon.class);
            startActivity(intent);



            // Handle the camera action

        } else if (id == R.id.Sidebar_userAuthority) {

            Intent intent= new Intent(getApplicationContext(), UserAuthority.class);
            startActivity(intent);


        } else if (id == R.id.Sidebar_connectedDevices) {

            Intent intent= new Intent(getApplicationContext(), ConnectedDevices.class);
            startActivity(intent);


        } else if (id == R.id.Sidebar_widget) {

            Intent intent= new Intent(getApplicationContext(), Widget.class);
            startActivity(intent);


        } else if (id == R.id.Sidebar_aboutJibcon) {
//
            Intent intent= new Intent(getApplicationContext(), AboutJibcon.class);
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
