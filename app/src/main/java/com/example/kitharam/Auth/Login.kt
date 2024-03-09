package com.example.kitharam.Auth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kitharam.R
import com.example.kitharam.UserActivity
import com.example.kitharam.api.LoginResponse
import com.example.kitharam.api.RetrofitClient
import com.example.kitharam.api.User
import com.example.kitharam.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var etusername: EditText
    private lateinit var etemail: EditText
    private lateinit var etpassword: EditText
    private lateinit var login: Button
    private lateinit var signup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etusername = findViewById(R.id.etusername)
        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        login = findViewById(R.id.Login)
        signup = findViewById(R.id.signup)

        binding.Login.setOnClickListener {
            val email = binding.etemail.text.toString().trim()
            val password = binding.etpassword.text.toString().trim()
            loginUser(email, password)
        }

        binding.signup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        val user = User(email = email, password = password)
        val apiService = RetrofitClient.getService(this)

        apiService.login(user).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val User = response.body()
                if (response.isSuccessful && User != null) {
                    val token = User.token
                    RetrofitClient.saveToken(this@Login, token)
                    val intent = Intent(this@Login, UserActivity::class.java)
                    startActivity(intent)
                    finish()
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