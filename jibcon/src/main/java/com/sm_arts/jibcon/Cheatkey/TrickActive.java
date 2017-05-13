package com.sm_arts.jibcon.Cheatkey;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.sm_arts.jibcon.R;
import java.util.ArrayList;

/**
 * Created by WooJin & ChanJoo on 2017-04-14.
 */

public class TrickActive extends android.support.v4.app.Fragment{
    GridView gridView=null;

    ArrayList<Bitmap> picArr = new ArrayList<Bitmap>();
    ArrayList<String> textArr = new ArrayList<String>();


    ImageButton fab;
    boolean expanded = false;
    private View fabItem1;
    private TextView fabItem2;
    private float offset1;
    private float offset2;

        public void floatingbuttoninit(final View root)
        {
            final ViewGroup fabcontainer = (ViewGroup)root.findViewById(R.id.fab_container_cheatkey);
            fabItem1=root.findViewById(R.id.fab_action_1_cheatkey);
            fabItem2=(TextView)root.findViewById(R.id.Txt_floating_cheatkey);

            fab=(ImageButton) root.findViewById(R.id.fab_cheatkey);
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


    public TrickActive(){}

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.fan);
        Bitmap img0 = drawable1.getBitmap();
        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.option);
        Bitmap img1 = drawable2.getBitmap();
        BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.bulb);
        Bitmap img2 = drawable3.getBitmap();
        BitmapDrawable drawable4 = (BitmapDrawable) getResources().getDrawable(R.drawable.fan);
        Bitmap img3 = drawable4.getBitmap();
        BitmapDrawable drawable5 = (BitmapDrawable) getResources().getDrawable(R.drawable.refrigerator);
        Bitmap img4 = drawable5.getBitmap();
        BitmapDrawable drawable6 = (BitmapDrawable) getResources().getDrawable(R.drawable.bulb);
        Bitmap img5 = drawable6.getBitmap();
        BitmapDrawable drawable7 = (BitmapDrawable) getResources().getDrawable(R.drawable.option);
        Bitmap img6 = drawable7.getBitmap();
        BitmapDrawable drawable8 = (BitmapDrawable) getResources().getDrawable(R.drawable.bulb);
        Bitmap img7 = drawable8.getBitmap();

        picArr.add(img0);
        picArr.add(img1);
        picArr.add(img2);
        picArr.add(img3);
        picArr.add(img4);
        picArr.add(img5);
        picArr.add(img6);
        picArr.add(img7);

        for(int i = 1 ; i < 9 ; i++){
            textArr.add("Cheat Key #"+Integer.toString(i));
        }

        View rootView = inflater.inflate(R.layout.cheatkey_active, container, false);

        gridView = (GridView)rootView.findViewById(R.id.maingridView);
        gridView.setAdapter(new Myadapter());


        floatingbuttoninit(rootView);

        return rootView;
    }

    /* Customized Adapter */
    public class Myadapter extends BaseAdapter {
        LayoutInflater inflater;

        public Myadapter(){
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 질문
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return picArr.size();
        }

        @Override
        public Object getItem(int position) {
            return picArr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = inflater.inflate(R.layout.cheatkey_active_item,parent,false);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.itemImageView);
            TextView textView = (TextView) convertView.findViewById(R.id.itemtextView);

            imageView.setImageBitmap(picArr.get(position));
            textView.setText(textArr.get(position));

            return convertView;
        }
    }
}
