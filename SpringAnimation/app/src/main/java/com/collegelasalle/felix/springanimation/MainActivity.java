package com.collegelasalle.felix.springanimation;

import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean isLeft = true;
    private boolean isUp = true;
    private boolean isBig = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView = findViewById(R.id.imageView);

        Button xButton = findViewById(R.id.xButton);
        xButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SpringAnimation anim = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_X,
                        isLeft ? 500 : 0);
                isLeft = !isLeft;
                anim.getSpring().setStiffness(SpringForce.STIFFNESS_HIGH);
                anim.start();
            }
        });

        Button yButton = findViewById(R.id.yButton);
        yButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SpringAnimation anim = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y,
                        isUp ? 500 : 0);
                isUp = !isUp;
                anim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
                anim.start();
            }
        });

        Button sButton = findViewById(R.id.sButton);
        sButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SpringAnimation animX = new SpringAnimation(imageView, DynamicAnimation.SCALE_X,
                        isBig ? 0.5f : 1);
                animX.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
                animX.start();
                SpringAnimation animY = new SpringAnimation(imageView, DynamicAnimation.SCALE_Y,
                        isBig ? 0.5f : 1);
                animY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
                animY.start();
                isBig = !isBig;
            }
        });
    }
}
