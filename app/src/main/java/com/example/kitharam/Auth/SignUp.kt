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
import com.example.kitharam.api.DefaultResponse
import com.example.kitharam.api.RetrofitClient
import com.example.kitharam.api.User
import com.example.kitharam.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

                RetrofitClient.instance.register(name, email, password, cpassword)
                    .enqueue(object : Callback<DefaultResponse> {
                        override fun onResponse(
                            call: Call<DefaultResponse>,
                            response: Response<DefaultResponse>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    response.body()?.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(
                                applicationContext,
                                "Email Already Exist",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    })
            }
        }
    }


