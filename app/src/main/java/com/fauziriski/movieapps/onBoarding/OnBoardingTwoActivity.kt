package com.fauziriski.movieapps.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fauziriski.movieapps.R
import kotlinx.android.synthetic.main.activity_on_boarding_one.*
import kotlinx.android.synthetic.main.activity_on_boarding_two.*

class OnBoardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)

        btn_next_onboarding.setOnClickListener {
            finishAffinity()
            var moveOnBoarding = Intent(this@OnBoardingTwoActivity, OnBoardingThreeActivity::class.java)
            startActivity(moveOnBoarding)
        }
    }
}