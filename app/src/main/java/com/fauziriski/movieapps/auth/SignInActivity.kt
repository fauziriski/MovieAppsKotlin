package com.fauziriski.movieapps.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fauziriski.movieapps.HomeActivity
import com.fauziriski.movieapps.R
import com.fauziriski.movieapps.model.User
import com.fauziriski.movieapps.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String
    lateinit var mDatabase: DatabaseReference
    lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")
        preferences = Preferences(this)
        preferences.setValues("onBoarding", "1")

        if (preferences.getValues("status").equals("1")) {
            finishAffinity()

            var moveHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(moveHome)
        }
        btn_signin.setOnClickListener{
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")) {
                et_username.error = "Username anda masih kosong"
                et_username.requestFocus()
            } else if (iPassword.equals("")) {
                et_password.error = "Password anda masih kosong"
                et_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }

        }

        btn_sign_up.setOnClickListener {
            var moveSignUp = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(moveSignUp)
        }


    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message,
                        Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "Username Tidak Ditemukan",
                            Toast.LENGTH_LONG).show()

                }else {
                    if (user.password.equals(iPassword)) {

                        preferences.setValues("username", user.username.toString())
                        preferences.setValues("email", user.email.toString())
                        preferences.setValues("password", user.password.toString())
                        preferences.setValues("name", user.name.toString())
                        preferences.setValues("status", "1")

                        finishAffinity()
                        var moveHome = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(moveHome)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password salah", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })


    }
}