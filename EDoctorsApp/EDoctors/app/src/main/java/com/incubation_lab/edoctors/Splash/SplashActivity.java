package com.incubation_lab.edoctors.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.incubation_lab.edoctors.R;
import com.incubation_lab.edoctors.Splash.ui.ViewPagerAdapter;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    LinearProgressIndicator indicator;
   ViewPager2 splashViewPager;
    ConstraintLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.splash_logo);
        indicator=findViewById(R.id.splash_progress_indicator);
        splashViewPager = findViewById(R.id.splash_viewpager);
        container=findViewById(R.id.splash_container);
        AlphaAnimation animation = new AlphaAnimation(.2f,1.0f);
        animation.setDuration(1000);
        logo.startAnimation(animation);

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(indicator,"progress",0,100);
        progressAnimator.setDuration(1000);
        progressAnimator.start();
        progressAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
              splashViewPager.setAdapter(new ViewPagerAdapter( SplashActivity.this));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }
}