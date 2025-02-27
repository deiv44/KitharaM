package com.example.kitharam.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitharam.R
import com.example.kitharam.models.User
import com.example.kitharam.Adapter.UserAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HomeUserFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private val userList = ArrayList<User>()
    private val db = FirebaseFirestore.getInstance() // Firestore instance

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_user, container, false)

        recyclerView = rootView.findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userAdapter = UserAdapter(userList) { category ->
            replaceFragment(category)
        }
        recyclerView.adapter = userAdapter

        fetchUsersFromFirestore()

        return rootView
    }

    private fun fetchUsersFromFirestore() {
        db.collection("users").get()
            .addOnSuccessListener { documents ->
                userList.clear()
                for (document in documents) {
                    val user = document.toObject(User::class.java)
                    userList.add(user)
                }
                userAdapter.notifyDataSetChanged() // Refresh RecyclerView
                Log.d("FirestoreData", "Fetched Users: $userList")
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreError", "Error getting users", e)
            }
    }

    private fun replaceFragment(category: String) {
        val fragment = when (category) {
            "Beginner" -> BeginnerFragment()
            "Intermediate" -> IntermediateFragment()
            "Advanced" -> AdvanceFragment()
            else -> null
        }

        if (fragment != null) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameUser, fragment) // Replace fragment in UserActivity
                .addToBackStack(null) // Enables back button navigation
                .commit()
        }
    }


}
