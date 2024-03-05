package com.example.kitharam.Auth

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.UserHandle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

//        binding.btnSignup.setOnClickListener {
//            checkRegister()
//        }

        btnlogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener{

            val name = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val cpassword = etConfirmation.text.toString().trim()

            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password != cpassword) {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(name = name, email = email, password = password)
            val apiService = RetrofitClient.getService(this)
            apiService.register(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Registration Successful",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Registration Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
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


//    private fun checkRegister() {
//        username = etUsername.text.toString()
//        email = etEmail.text.toString()
//        password = etPassword.text.toString()
//        confirmation = etConfirmation.text.toString()
//
//        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
//            alertFail("Name, Email and Password is Required")
//        } else if (password != confirmation) {
//            alertFail("Password and Password Confirmation doesn't match.")
//        } else {
////            callRegisterApi(username, email, password)
//             true
//        }
//    }

//    private fun callRegisterApi(username: String, email: String, password: String) {
//        val call: Call<RegisterModelResponse> = APIClient.getInstance().api.registerApi(username, email, password)
//        call.enqueue(object : Callback<RegisterModelResponse> {
//            override fun onResponse(call: Call<RegisterModelResponse>, response: Response<RegisterModelResponse>) {
//                if (response.isSuccessful) {
//                    Toast.makeText(this@Signup, "success", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this@Signup, Login::class.java))
//                    Toast.makeText(this@Signup, response.body()?.message, Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this@Signup, "Failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterModelResponse>, t: Throwable) {
//                Toast.makeText(this@Signup, "error", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

//    private fun alertSuccess(s: String) {
//        AlertDialog.Builder(this)
//            .setTitle("Success")
//            .setIcon(R.drawable.baseline_email_24)
//            .setMessage(s)
//            .setPositiveButton("Login") { dialog, which ->
//                dialog.dismiss()
//                val intent = Intent(this, Login::class.java)
//                startActivity(intent)
//            }
//            .show()
//    }
//
//    private fun alertFail(s: String) {
//        AlertDialog.Builder(this)
//            .setTitle("Failed")
//            .setIcon(R.drawable.baseline_info_24)
//            .setMessage(s)
//            .setPositiveButton("OK") { dialog, which ->
//                dialog.dismiss()
//            }
//            .show()
//    }
//}
