package com.incubation_lab.edoctor

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View

import android.view.animation.AlphaAnimation
import androidx.core.animation.addListener
import com.incubation_lab.edoctor.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val animation = AlphaAnimation(.2f,1.0f)
        animation.duration = 1000
        binding.splashLogo.startAnimation(animation)

        val progressAnimator:ObjectAnimator = ObjectAnimator.ofInt(binding.splashProgressIndicator,"progress",0,100)
        progressAnimator.duration = 1000
        progressAnimator.start()
        progressAnimator.addListener(onEnd = {
//            TransitionManager.beginDelayedTransition(view)
//            binding.splashPlaceholder.setContentId(R.id.splash_logo)
//            binding.splashProgressIndicator.visibility = View.GONE
//            binding.splashLogo.visibility = View.GONE
            binding.splashViewpager.adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        })


    }
}