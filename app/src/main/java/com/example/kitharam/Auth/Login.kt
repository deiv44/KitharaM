package com.example.kitharam.Auth

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var etemail: EditText
    private lateinit var etpassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnlogin = findViewById(R.id.login)
        btnsignup = findViewById(R.id.signup)

        btnsignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {

            val email = etemail.text.toString().trim()
            val password = etpassword.text.toString().trim()

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
                    response: Response<LoginResponse>){

                    if(response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(applicationContext, UserActivity::class.java)
                        startActivity(intent)
                        startActivity(intent)

                    } else if (response.errorBody() != null) {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = errorResponse ?: "Something went wrong."
                        Toast.makeText(
                            applicationContext,
                            errorMessage,
                            Toast.LENGTH_LONG
                        ).show()
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