package com.example.kitharam.Auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kitharam.R
import com.google.firebase.auth.FirebaseAuth

class ForgotpassFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var resetPasswordButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgotpass, container, false)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Get references to UI elements
        emailEditText = view.findViewById(R.id.etEmail)
        resetPasswordButton = view.findViewById(R.id.btnResetPassword)

        // Set up click listener for the reset button
        resetPasswordButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                // Send a password reset email using Firebase Authentication
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "Reset link sent to your email", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(requireContext(), "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}
