package com.example.kitharam.Auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kitharam.R

class ForgotPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.forgot_fragment_container, ForgotpassFragment())
                .commit()
        }
    }
}
