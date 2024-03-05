package com.example.kitharam.Auth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kitharam.R
import com.example.kitharam.TutorActivity
import com.example.kitharam.UserActivity
import com.example.kitharam.api.LoginResponse
import com.example.kitharam.api.RetrofitClient
import com.example.kitharam.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : AppCompatActivity() {


    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var loginbttn: Button
    private lateinit var signupbttn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etusername)
        etEmail = findViewById(R.id.etemail)
        etPassword = findViewById(R.id.etpassword)
        loginbttn = findViewById(R.id.Login)
        signupbttn = findViewById(R.id.signup)


        loginbttn.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            loginUser(email, password)
        }

        signupbttn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        val user = User(email = email, password = password)
        val apiService = RetrofitClient.getService(this)

        apiService.login(user).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()
                if (response.isSuccessful && loginResponse != null) {
                    val token = loginResponse.token
                    RetrofitClient.saveToken(this@Login, token)
//                    saveUserData(loginResponse.user)
                    Toast.makeText(
                        applicationContext,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}