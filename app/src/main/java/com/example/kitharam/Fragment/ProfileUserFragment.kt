package com.example.kitharam.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kitharam.Auth.Login
import com.example.kitharam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class ProfileUserFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var logoutButton: Button
    private lateinit var aboutMeEditText: EditText
    private lateinit var saveAboutMeButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_user, container, false)

        // Initialize Firebase Auth and Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Reference views from XML
        nameTextView = view.findViewById(R.id.profileNameTextView)
        emailTextView = view.findViewById(R.id.profileEmailTextView)
        logoutButton = view.findViewById(R.id.bttnlogout)
        aboutMeEditText = view.findViewById(R.id.editAboutMe)
        saveAboutMeButton = view.findViewById(R.id.btnSaveAboutMe)

        // Fetch user details
        fetchUserDetails()
        // Load the "About Me" field from Firestore
        loadAboutMe()

        // Logout button: sign out and navigate to Login.kt
        logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
            activity?.finish()
        }

        // Save button: save the updated "About Me" text to Firestore
        saveAboutMeButton.setOnClickListener {
            saveAboutMe()
        }

        return view
    }

    private fun fetchUserDetails() {
        val user = auth.currentUser
        if (user != null) {
            nameTextView.text = user.displayName ?: "Keilizon-Deiv"
            emailTextView.text = user.email ?: "No Email Available"
        }
    }

    private fun loadAboutMe() {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val aboutMe = document.getString("aboutMe")
                    aboutMeEditText.setText(aboutMe)
                }
            }
            .addOnFailureListener { exception ->
                // Handle error (for example, log the error or show a Toast)
            }
    }

    private fun saveAboutMe() {
        val userId = auth.currentUser?.uid ?: return
        val aboutMeText = aboutMeEditText.text.toString()
        val data = hashMapOf("aboutMe" to aboutMeText)

        firestore.collection("prooile").document(userId)
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                // Optionally notify the user that the update was successful (e.g., with a Toast)
            }
            .addOnFailureListener { exception ->
                // Handle errors (e.g., show an error Toast)
            }
    }
}
