package com.example.kitharam.Auth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kitharam.Fragment.HomeUserFragment
import com.example.kitharam.R
import com.example.kitharam.UserActivity
import com.example.kitharam.api.DefaultResponse
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

        binding.signup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        binding.Login.setOnClickListener {
            val email = binding.etemail.text.toString().trim()
            val password = binding.etpassword.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            RetrofitClient.instance.login(email, password).enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ){
                    if(!response.body()?.error!!){
                        val intent = Intent(applicationContext, HomeUserFragment::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)
                    }else{
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Something went wrong.",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })

        }
    }
}