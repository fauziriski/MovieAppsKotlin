package com.fauziriski.movieapps.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fauziriski.movieapps.R
import com.fauziriski.movieapps.auth.SignInActivity
import com.fauziriski.movieapps.utils.Preferences
import kotlinx.android.synthetic.main.activity_on_boarding_one.*

class OnBoardingOneActivity : AppCompatActivity() {
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        preferences= Preferences(this)

        if (preferences.getValues("onBoarding").equals("1")) {
            finishAffinity()

            var moveLogin = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(moveLogin)
        }

        btn_next.setOnClickListener {
            finishAffinity()
            var moveOnBoarding = Intent(this@OnBoardingOneActivity, OnBoardingTwoActivity::class.java)
            startActivity(moveOnBoarding)
        }

        btn_login.setOnClickListener{
            finishAffinity()
            var moveLogin = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(moveLogin)
        }

    }
}