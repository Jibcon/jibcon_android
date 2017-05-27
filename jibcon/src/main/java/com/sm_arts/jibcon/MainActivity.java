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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm_arts.jibcon.app.Cheatkey.TrickMenuActivity;
import com.sm_arts.jibcon.app.Conshop.MarketMenuActivity;
import com.sm_arts.jibcon.Device.DeviceMenuFragment;
import com.sm_arts.jibcon.app.Settings.SettingActivity;
import com.sm_arts.jibcon.app.SidebarMenu.AboutJibconActivity;
import com.sm_arts.jibcon.app.SidebarMenu.ConnectedDevicesActivity;
import com.sm_arts.jibcon.app.SidebarMenu.MyJibconActivity;
import com.sm_arts.jibcon.app.SidebarMenu.UserAuthorityActivity;
import com.sm_arts.jibcon.app.SidebarMenu.WidgetActivity;
import com.sm_arts.jibcon.app.Usermenu.UserMenuActivity;
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
                    btn1.setImageResource(R.drawable.ic_home_blue_48dp);
                    btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    return devicemenu;
                case 1:
                    Log.d("FragmentCheck","ToTrick");
                    btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    btn2.setImageResource(R.drawable.ic_link_blue_48dp);
                    btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    return trickmenu;
                case 2:
                    Log.d("FragmentCheck","ToMarket");
                    btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    btn3.setImageResource(R.drawable.ic_shopping_cart_blue_48dp);
                    btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    return marketmenu;
                case 3:
                    Log.d("FragmentCheck","ToUserMenu");
                    btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    btn4.setImageResource(R.drawable.ic_pie_chart_blue_48dp);
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

            switch (tag)
            {
                case 0 :
                    btn1.setImageResource(R.drawable.ic_home_blue_48dp);
                    btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    break;
                case 1:
                    btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    btn2.setImageResource(R.drawable.ic_link_blue_48dp);
                    btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    break;
                case 2:
                    btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    btn3.setImageResource(R.drawable.ic_shopping_cart_blue_48dp);
                    btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                    break;
                case 3:
                    btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                    btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                    btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                    btn4.setImageResource(R.drawable.ic_pie_chart_blue_48dp);
                    break;
            }

            vp.setCurrentItem(tag);

        }
    };

    private  void initLayout()
    {
        devicemenu = new DeviceMenuFragment();
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
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                        btn1.setImageResource(R.drawable.ic_home_blue_48dp);
                        btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                        btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                        btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                        break;
                    case 1:
                        btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                        btn2.setImageResource(R.drawable.ic_link_blue_48dp);
                        btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                        btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                        break;
                    case 2:
                        btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                        btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                        btn3.setImageResource(R.drawable.ic_shopping_cart_blue_48dp);
                        btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
                        break;
                    case 3:
                        btn1.setImageResource(R.drawable.ic_home_gray_48dp);
                        btn2.setImageResource(R.drawable.ic_link_gray_48dp);
                        btn3.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
                        btn4.setImageResource(R.drawable.ic_pie_chart_blue_48dp);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: "+state);
            }
        });
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

        //toolbar search tab  : res-> menu
        //toolbar hamburder menu :  style->ToolbarColoredBackArrow

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        app = (GlobalApplication)getApplicationContext();

       // toolbar.setNavigationIcon(R.mipmap.hamburder_menu);

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
        btn1.setImageResource(R.drawable.ic_home_blue_48dp);
        btn4.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
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

//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        ToastHelper.toast(getApplicationContext(),"검색");
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
//        menu.
//        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                ToastHelper.toast(getApplicationContext(),"검색");
//
//                return false;
//            }
//        });
        return super.onMenuOpened(featureId, menu);

    }
}
