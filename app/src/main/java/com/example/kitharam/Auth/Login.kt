package com.example.kitharam.Auth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kitharam.R
import android.app.AlertDialog
import android.widget.EditText
import android.widget.Toast
import com.example.kitharam.UserActivity

class Login : AppCompatActivity() {


    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var btnforgot: Button
    private var email: String = ""
    private var password: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etemail)
        etPassword = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.Login)
        btnRegister = findViewById(R.id.signup)
        btnforgot = findViewById(R.id.forgot)

        btnLogin.setOnClickListener {
            checkLogin()
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        btnforgot.setOnClickListener {
            val intent = Intent(this, ForgotpassFragment::class.java)
            startActivity(intent)
        }
    }

    private fun checkLogin() {
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            alertFail("Email and Password is required.")
        } else {
            sendLogin()
        }
    }

    private fun sendLogin() {
        Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }

    private fun alertFail(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Failed")
            .setIcon(R.drawable.baseline_info_24)
            .setMessage(s)
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}