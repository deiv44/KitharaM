package com.example.kitharam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kitharam.Auth.SignUp
import com.example.kitharam.Auth.SignupTr

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var Signupstudent = findViewById<Button>(R.id.signupstudent)
        var Signuptutor = findViewById<Button>(R.id.signuptutor)

        Signupstudent.setOnClickListener{
            val intent1 = Intent(this, SignUp::class.java)
            startActivity(intent1)
        }
        Signuptutor.setOnClickListener{
            val intent = Intent(this, SignupTr::class.java)
            startActivity(intent)
        }
    }
}
