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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginTr : AppCompatActivity() {


    private lateinit var etemail: EditText
    private lateinit var etpassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_tr)


        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnlogin = findViewById(R.id.logintr)
        btnsignup = findViewById(R.id.signuptr)

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

            RetrofitClient.instance.logintr(email, password).enqueue(object :
                Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ){

                    if(response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(applicationContext, TutorActivity::class.java)
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