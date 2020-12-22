package com.fauziriski.movieapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fauziriski.movieapps.onBoarding.OnBoardingOneActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val move = Handler()
        move.postDelayed({
            var movePage = Intent(this@SplashActivity, OnBoardingOneActivity::class.java)
            startActivity(movePage)
        }, 4000)
    }
}