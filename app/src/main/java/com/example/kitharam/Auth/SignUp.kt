package com.example.kitharam.Auth

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.UserHandle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kitharam.Frontauth
import com.example.kitharam.R
import com.example.kitharam.UserActivity
import com.example.kitharam.api.RetrofitClient
import com.example.kitharam.api.User
import com.example.kitharam.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignUp : AppCompatActivity() {

        private lateinit var binding: ActivitySignUpBinding

        private lateinit var etUsername: EditText
        private lateinit var etEmail: EditText
        private lateinit var etPassword: EditText
        private lateinit var etConfirmation: EditText
        private lateinit var btnlogin: Button
        private lateinit var btnSignUp: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySignUpBinding.inflate(layoutInflater)
            setContentView(binding.root)

            etUsername = findViewById(R.id.etusername)
            etEmail = findViewById(R.id.etemail)
            etPassword = findViewById(R.id.etpassword)
            etConfirmation = findViewById(R.id.etConpassword)
            btnlogin = findViewById(R.id.btnlogin)
            btnSignUp = findViewById(R.id.btnSignup)

            btnlogin.setOnClickListener {
                val intent = Intent(this, Frontauth::class.java)
                startActivity(intent)
            }

            btnSignUp.setOnClickListener {

                val name = etUsername.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()
                val cpassword = etConfirmation.text.toString().trim()

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (password != cpassword) {
                    Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val user = User(name = name, email = email, password = password)
                val apiService = RetrofitClient.getService(this)
                apiService.register(user).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            val registeredUser = response.body()
                            if (registeredUser != null) {
                                val intent = Intent(this@SignUp, Frontauth::class.java)
                                startActivity(intent)
                                finish()
                                // Registration successful, handle accordingly
                                Toast.makeText(
                                    applicationContext,
                                    "Registration Successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                // Null response body, handle error
                                Toast.makeText(
                                    applicationContext,
                                    "Registration failed: Null response body",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            // Registration failed, handle error
                            Toast.makeText(
                                applicationContext,
                                "Registration failed: ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        // Request failed, handle error
                        Toast.makeText(
                            applicationContext,
                            "Error: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }


