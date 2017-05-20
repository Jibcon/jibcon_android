//package com.example.admin.jibcon.Cheatkey;
package com.sm_arts.jibcon.Cheatkey;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.sm_arts.jibcon.R;

import java.util.ArrayList;

/**
 * Created by WooJin on 2017-04-14.
 */

public class TrickPassive extends android.support.v4.app.Fragment{
    ArrayList<String> txtArr1 = new ArrayList<String>();
    ArrayList<String> txtArr2 = new ArrayList<String>();

    ListView listView;
    public TrickPassive(){}
    ImageButton fab;
    boolean expanded = false;
    private View fabItem1;
    private TextView fabItem2;
    private float offset1;
    private float offset2;

    public void floatingbuttoninit(final View root)
    {
        final ViewGroup fabcontainer = (ViewGroup)root.findViewById(R.id.fab_container_cheatkey_passive);
        fabItem1=root.findViewById(R.id.fab_action_1_cheatkey_passive);
        fabItem2=(TextView)root.findViewById(R.id.Txt_floating_cheatkey_passive);

        fab=(ImageButton) root.findViewById(R.id.fab_cheatkey_passive);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded=!expanded;
                if(expanded)
                {
                    expandFab();
                    fabItem1.setVisibility(View.VISIBLE);
                    fabItem2.setVisibility(View.VISIBLE);

                }
                else
                {
                    collapseFab();
                    fabItem1.setVisibility(View.INVISIBLE);
                    fabItem2.setVisibility(View.INVISIBLE);

                }
            }
        });

        fabcontainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fabcontainer.getViewTreeObserver().removeOnPreDrawListener(this);
                offset1 = fab.getY() - fabItem1.getY();
                fabItem1.setTranslationY(offset1);
                offset2 = fab.getY() - fabItem2.getY();
                fabItem2.setTranslationY(offset2);
                return true;
            }
        });

    }



    private void collapseFab() {
        // fab.setImageResource(R.drawable.animated_minus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createCollapseAnimator(fabItem1, offset1),
                createCollapseAnimator(fabItem2, offset2));
        animatorSet.start();
        animateFab();
    }

    private void expandFab() {
        //.setImageResource(R.drawable.animated_plus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createExpandAnimator(fabItem1, offset1),
                createExpandAnimator(fabItem2, offset2));
        animatorSet.start();
        animateFab();
    }

    private static final String TRANSLATION_Y = "translationY";

    private Animator createCollapseAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private Animator createExpandAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private void animateFab() {
        Drawable drawable = fab.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        txtArr1.add("방의 온도 < 24 ");
        txtArr2.add("-난방 on, -전기장판 on");

        txtArr1.add("방의 온도가 > 30");
        txtArr2.add("-에어컨 on, -공기청정기 on");

        txtArr1.add("새벽인데 코딩중");
        txtArr2.add("소리벗고 바지질러");

        txtArr1.add("여자친구");
        txtArr2.add("off");



        View rootView2 = inflater.inflate(R.layout.cheatkey_passive, container, false);

        listView = (ListView)rootView2.findViewById(R.id.passiveListView);
        listView.setAdapter(new Myadapter());

        floatingbuttoninit(rootView2);


        return rootView2;
    }
    public class Myadapter extends BaseAdapter {
        LayoutInflater inflater;

        public Myadapter(){
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            notifyDataSetChanged();

        }

        @Override
        public int getCount() {
            return txtArr1.size();
        }

        @Override
        public Object getItem(int position) {
            return txtArr1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView = inflater.inflate(R.layout.cheatkey_passive_item,parent,false);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.passiveTextView1);
            textView.setText(txtArr1.get(position));

            TextView textView2 = (TextView) convertView.findViewById(R.id.passiveTextView2);
            textView2.setText(txtArr2.get(position));

            return convertView;
        }
    }
}
