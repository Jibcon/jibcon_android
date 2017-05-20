package com.sm_arts.jibcon.Device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sm_arts.jibcon.Device.service.DeviceService;
import com.sm_arts.jibcon.Device.service.DeviceServiceImpl;
import com.sm_arts.jibcon.GlobalApplication;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.UI.FloatingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-03-30.
 */

public class DeviceMenuActivity extends Fragment {
    private final String TAG = "jibcon/"+getClass().getSimpleName();

    boolean expanded = false;
    private View fabItem1;
    private TextView fabItem2;
    private View fabItem3;
    ImageButton fab;

    private float offset1;
    private float offset2;
    private float offset3;
    SwipeRefreshLayout swiperefreshlayout;

    //Button button;
    static myGridView DeviceGridview;
    DeviceMenuAdapter adapter;
    GlobalApplication app;


//
//    public void goToAddDevice()
//    {
//        Intent intent = new Intent(getActivity().getApplicationContext(), AddDeviceActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//    }
//    public void floatingbuttoninit(final View root, final ViewGroup container,LayoutInflater inflater)
//    {
//
//        final ViewGroup fabcontainer = (ViewGroup)root.findViewById(R.id.fab_container_device);
//        fabItem1=root.findViewById(R.id.fab_action_1_device);
//        fabItem2=(TextView)root.findViewById(R.id.Txt_floating_device);
//
//        fabItem1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToAddDevice();
//
//
//            }
//        });
//        fab=(ImageButton) root.findViewById(R.id.fab_device);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expanded=!expanded;
//                if(expanded)
//                {
//
//                    expandFab();
//                    fabItem1.setVisibility(View.VISIBLE);
//                    fabItem2.setVisibility(View.VISIBLE);
//
//                }
//                else
//                {
//
//                    collapseFab();
//                    fabItem1.setVisibility(View.INVISIBLE);
//                    fabItem2.setVisibility(View.INVISIBLE);
//
//                }
//            }
//        });
//
//        fabcontainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                fabcontainer.getViewTreeObserver().removeOnPreDrawListener(this);
//                offset1 = fab.getY() - fabItem1.getY();
//                fabItem1.setTranslationY(offset1);
//                offset2 = fab.getY() - fabItem2.getY();
//                fabItem2.setTranslationY(offset2);
//                return true;
//            }
//        });
//
//    }

    private View initLayout(LayoutInflater inflater, ViewGroup container)
    {
            View root = inflater.inflate(R.layout.menu_divice,container,false);

        ImageButton fab= (ImageButton)root.findViewById(R.id.fab_device_behind);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(),FloatingActivity.class));
                //getActivity().finish();
            }
        });
        swiperefreshlayout=(SwipeRefreshLayout)root.findViewById(R.id.swipelayout_menu_deivce);

        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DeviceServiceImpl.getInstance().reloadDeviceItems(new DeviceService.onSuccessListener() {
                    @Override
                    public void onSuccessGetDeviceItems(List<DeviceItem> deviceItems) {
                        adapter.setDeviceItems((ArrayList)deviceItems);
                        adapter.notifyDataSetChanged();
                        swiperefreshlayout.setRefreshing(false);
                    }
                });
            }
        });

        DeviceGridview = (myGridView)root.findViewById(R.id.ScrollViewDevice);

        DeviceGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: "+adapter.getDeviceItems().get(position).getDeviceName());
//                Toast.makeText(getActivity().getApplicationContext(),adapter.getDeviceItems().get(position).getDeviceName(),Toast.LENGTH_SHORT).show();
                //
            }
        });
        // TODO: 2017-04-06 그리드뷰에 리스너 달기

        adapter = new DeviceMenuAdapter(getActivity().getApplicationContext());
        adapter.setDeviceItems(new ArrayList<DeviceItem>());
        DeviceGridview.setAdapter(adapter);

        Log.d(TAG, "onCreateView: ");
        DeviceServiceImpl.getInstance().getDeviceItems(new DeviceService.onSuccessListener() {
            @Override
            public void onSuccessGetDeviceItems(List<DeviceItem> deviceItems) {
                Log.d(TAG, "onSuccessGetDeviceItems: ");
                adapter.setDeviceItems((ArrayList)deviceItems);
                adapter.notifyDataSetChanged();
            }
        });

        //floatingbuttoninit(root,container, inflater);


        return root;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        app=(GlobalApplication)getActivity().getApplicationContext();


        Log.d("FragmentCheck","DeviceMenuActivity onCreateView");
        View root=initLayout(inflater,container);
        return root;
    }
//
//    private void collapseFab() {
//        // fab.setImageResource(R.drawable.animated_minus);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(createCollapseAnimator(fabItem1, offset1),
//                createCollapseAnimator(fabItem2, offset2),
//                createCollapseAnimator(fabItem3, offset3));
//        animatorSet.start();
//        animateFab();
//    }
//
//    private void expandFab() {
//        //.setImageResource(R.drawable.animated_plus);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(createExpandAnimator(fabItem1, offset1),
//                createExpandAnimator(fabItem2, offset2),
//                createExpandAnimator(fabItem3, offset3));
//        animatorSet.start();
//        animateFab();
//    }
//
//    private static final String TRANSLATION_Y = "translationY";
//
//    private Animator createCollapseAnimator(View view, float offset) {
//        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset)
//                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
//    }
//
//    private Animator createExpandAnimator(View view, float offset) {
//        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
//                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
//    }
//
//    private void animateFab() {
//        Drawable drawable = fab.getDrawable();
//        if (drawable instanceof Animatable) {
//            ((Animatable) drawable).start();
//        }
//    }

}
