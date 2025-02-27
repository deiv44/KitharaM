package com.example.kitharam.Auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kitharam.R
import com.example.kitharam.UserActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var etemail: EditText
    private lateinit var etpassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnlogin = findViewById(R.id.login)
        btnsignup = findViewById(R.id.signup)

        auth = FirebaseAuth.getInstance()

        btnsignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email = etemail.text.toString().trim()
            val password = etpassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase Authentication for login
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, UserActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
