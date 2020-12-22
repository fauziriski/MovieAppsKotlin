package com.fauziriski.movieapps.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fauziriski.movieapps.R
import com.fauziriski.movieapps.auth.SignInActivity
import kotlinx.android.synthetic.main.activity_on_boarding_three.*
import kotlinx.android.synthetic.main.activity_on_boarding_two.*

class OnBoardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)

        btn_login.setOnClickListener {
            finishAffinity()
            var moveLogin = Intent(this@OnBoardingThreeActivity, SignInActivity::class.java)
            startActivity(moveLogin)
        }
    }
}