package com.fauziriski.movieapps.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fauziriski.movieapps.HomeActivity
import com.fauziriski.movieapps.R
import com.fauziriski.movieapps.model.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var firebase : FirebaseDatabase
    lateinit var database : DatabaseReference
    lateinit var databaseReference: DatabaseReference

    lateinit var iUsername : String
    lateinit var iPassword : String
    lateinit var iName : String
    lateinit var iEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebase = FirebaseDatabase.getInstance()
        database = FirebaseDatabase.getInstance().getReference()
        databaseReference = firebase.getReference("Users")

        btn_signup.setOnClickListener {

            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()
            iName = et_name.text.toString()
            iEmail = et_email.text.toString()

            if (iUsername.equals("")) {
                et_username.error = "Silahkan isi username anda"
                et_username.requestFocus()
            }else if (iPassword.equals("")) {
                et_password.error = "Silahkan isi password anda"
                et_password.requestFocus()
            }else if (iName.equals("")) {
                et_name.error = "Silahkan isi nama anda"
                et_name.requestFocus()
            }else if (iEmail.equals("")) {
                et_email.error = "Silahkan isi email anda"
                et_email.requestFocus()
            }else {
                saveData(iUsername, iPassword, iName, iEmail)
            }

        }



    }

    private fun saveData(iUsername: String, iPassword: String, iName: String, iEmail: String) {
        var user = User()
        user.email = iEmail
        user.name = iName
        user.password = iPassword
        user.username = iUsername

        if (iUsername != null) {
            retreveData(iUsername, user)
        }


    }

    private fun retreveData(iUsername: String, data: User) {
        databaseReference.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, databaseError.message,
                        Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var getUserData = dataSnapshot.getValue(User::class.java)

                if (getUserData == null) {
                    databaseReference.child(iUsername).setValue(data)

                    var moveHome = Intent(this@SignUpActivity,
                            SignUpPhotoscreenActivity::class.java).putExtra("name", data?.name)

                    startActivity(moveHome)
                } else {
                    Toast.makeText(this@SignUpActivity, "Username sudah digunakan",
                            Toast.LENGTH_LONG).show()
                }
            }
            
        })
    }
}