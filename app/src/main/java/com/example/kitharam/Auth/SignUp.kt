package com.example.kitharam.Auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kitharam.Frontauth
import com.example.kitharam.R
import com.example.kitharam.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnlogin.setOnClickListener {
            val intent = Intent(this, Frontauth::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            val name = binding.etusername.text.toString().trim()
            val email = binding.etemail.text.toString().trim()
            val password = binding.etpassword.text.toString().trim()
            val cpassword = binding.etConpassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != cpassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase Authentication - Register User
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Frontauth::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
