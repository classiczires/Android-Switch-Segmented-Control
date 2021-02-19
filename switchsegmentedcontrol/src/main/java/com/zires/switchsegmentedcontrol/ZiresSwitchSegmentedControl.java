package com.zires.switchsegmentedcontrol;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.motion.widget.MotionLayout;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by ClassicZires on 7/30/2019.
 **/

public class ZiresSwitchSegmentedControl extends LinearLayout {

    private final MotionLayout motionLayout;
    private boolean transitionStart = false;

    public ZiresSwitchSegmentedControl(Context context) {
        super(context);
        motionLayout = (MotionLayout) LayoutInflater.from(context).inflate(R.layout.switch_layout, null);
        addView(motionLayout);

        initOnClick();
    }

    public ZiresSwitchSegmentedControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        motionLayout = (MotionLayout) LayoutInflater.from(context).inflate(R.layout.switch_layout, null);
        addView(motionLayout);

        LayoutParams layoutParams = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        setLayoutParams(layoutParams);
        motionLayout.setLayoutParams(layoutParams);
        requestLayout();


        initOnClick();
    }

    public ZiresSwitchSegmentedControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        motionLayout = (MotionLayout) LayoutInflater.from(context).inflate(R.layout.switch_layout, null);
        addView(motionLayout);

        initOnClick();
    }

    private void initOnClick() {
        transitionStart = true;
        motionLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transitionStart) {
                    transitionStart = false;
                    motionLayout.transitionToEnd();
                } else {
                    transitionStart = true;
                    motionLayout.transitionToStart();
                }
            }
        });
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {

            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });
    }
}
