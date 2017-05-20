package com.sm_arts.jibcon.FloatingButtonUi;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sm_arts.jibcon.R;

public class FlotingButtonActiveActivity extends Activity {
    boolean expanded = false;
    private View fabItem1;
    private TextView fabItem2;
    private View fabItem3;
    ImageButton fab;

    private float offset1;
    private float offset2;
    private float offset3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floting_button_active);



        final ViewGroup fabcontainer = (ViewGroup)findViewById(R.id.fab_container_cheatkey_active);

        fabcontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fabItem1=findViewById(R.id.fab_action_1_cheatkey_active);
        fabItem1.setVisibility(View.INVISIBLE);
        fabItem2=(TextView)findViewById(R.id.Txt_floating_cheatkey_active);


        fab=(ImageButton)findViewById(R.id.fab_cheatkey_active);

        expandFab();
        fabItem1.setVisibility(View.VISIBLE);
        fabItem2.setVisibility(View.VISIBLE);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseFab();
                fabItem1.setVisibility(View.INVISIBLE);
                fabItem2.setVisibility(View.INVISIBLE);

                finish();

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
                createCollapseAnimator(fabItem2, offset2),
                createCollapseAnimator(fabItem3, offset3));
        animatorSet.start();
        animateFab();
    }

    private void expandFab() {
        //.setImageResource(R.drawable.animated_plus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createExpandAnimator(fabItem1, offset1),
                createExpandAnimator(fabItem2, offset2),
                createExpandAnimator(fabItem3, offset3));
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
}
