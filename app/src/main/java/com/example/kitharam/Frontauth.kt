package com.example.kitharam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kitharam.Auth.Login
import com.example.kitharam.Auth.LoginTr
import com.example.kitharam.Auth.SignUp

class Frontauth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frontauth)

        var loginbttnst = findViewById<Button>(R.id.loginstudent)
        var loginbttntr = findViewById<Button>(R.id.logintutor)
        var signupbttn = findViewById<Button>(R.id.signupbtn)

        loginbttnst.setOnClickListener{
            val intent1 = Intent(this, Login::class.java)
            startActivity(intent1)
        }
        loginbttntr.setOnClickListener{
            val intent1 = Intent(this, LoginTr::class.java)
            startActivity(intent1)
        }
        signupbttn.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}
