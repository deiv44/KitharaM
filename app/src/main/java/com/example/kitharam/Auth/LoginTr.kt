package com.example.kitharam.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kitharam.R
import com.example.kitharam.TutorActivity


class LoginTr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_tr)


        val loginbttn = findViewById<Button>(R.id.Login)
        val signupbttn = findViewById<Button>(R.id.signup)

        loginbttn.setOnClickListener {
            val intent = Intent(this,  TutorActivity::class.java)
            startActivity(intent)
        }

        signupbttn.setOnClickListener {
            val intent = Intent(this, SignupTr::class.java)
            startActivity(intent)
        }


    }
}