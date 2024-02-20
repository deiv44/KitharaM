package com.example.kitharam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

@Suppress("DEPRECATION")
class Splashkit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashkit)
        Handler().postDelayed({
            val intent = Intent(this, Frontauth::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}