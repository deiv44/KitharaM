package com.example.kitharam.Auth

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kitharam.Frontauth
import com.example.kitharam.R
import com.example.kitharam.api.DefaultResponse
import com.example.kitharam.api.RetrofitClient
import com.example.kitharam.databinding.ActivitySignupTrBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupTr : AppCompatActivity() {
    private lateinit var binding: ActivitySignupTrBinding

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmation: EditText
    private lateinit var btnSignUptr: Button
    private lateinit var btnlogin: Button

    private var username: String = ""
    private var email: String = ""
    private var password: String = ""
    private var confirmation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySignupTrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etUsername = findViewById(R.id.etusername)
        etEmail = findViewById(R.id.etemail)
        etPassword = findViewById(R.id.etpassword)
        etConfirmation = findViewById(R.id.etConpassword)
        btnlogin = findViewById(R.id.btnlogin)
        btnSignUptr = findViewById(R.id.btnSignup)

        btnlogin.setOnClickListener {
            val intent = Intent(this, Frontauth::class.java)
            startActivity(intent)
        }

        btnSignUptr.setOnClickListener {

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
