package com.example.kitharam.Auth

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kitharam.R
import com.example.kitharam.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {
   ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etUsername = findViewById(R.id.etusername)
        etEmail = findViewById(R.id.etemail)
        etPassword = findViewById(R.id.etpassword)
        etConfirmation = findViewById(R.id.etConpassword)
        btnRegister = findViewById(R.id.btnSignup)
        btnlogin = findViewById(R.id.btnlogin)

        binding.btnSignup.setOnClickListener {
            checkRegister()
        }
        btnlogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun checkRegister() {
        username = etUsername.text.toString()
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        confirmation = etConfirmation.text.toString()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            alertFail("Name, Email and Password is Required")
        } else if (password != confirmation) {
            alertFail("Password and Password Confirmation doesn't match.")
        } else {
//            callRegisterApi(username, email, password)
             true
        }
    }

//    private fun callRegisterApi(username: String, email: String, password: String) {
//        val call: Call<RegisterModelResponse> = APIClient.getInstance().api.registerApi(username, email, password)
//        call.enqueue(object : Callback<RegisterModelResponse> {
//            override fun onResponse(cal private lateinit var binding: ActivitySignUpBinding
//
//    private lateinit var etUsername: EditText
//    private lateinit var etEmail: EditText
//    private lateinit var etPassword: EditText
//    private lateinit var etConfirmation: EditText
//    private lateinit var btnRegister: Button
//    private lateinit var btnlogin: Button
//
//    private var username: String = ""
//    private var email: String = ""
//    private var password: String = ""
//    private var confirmation: String = l: Call<RegisterModelResponse>, response: Response<RegisterModelResponse>) {
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

    private fun alertSuccess(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Success")
            .setIcon(R.drawable.baseline_email_24)
            .setMessage(s)
            .setPositiveButton("Login") { dialog, which ->
                dialog.dismiss()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            .show()
    }

    private fun alertFail(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Failed")
            .setIcon(R.drawable.baseline_info_24)
            .setMessage(s)
            .setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}
