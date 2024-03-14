package com.example.kitharam.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitharam.R



class HomeTutorFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_tutor, container, false)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val data = listOf(
            ("Beginner"),
            ("Intermediate"),
            ("Advanced"),
        ) // Sample data
        val adapter = Adapter(data) { category ->
            // Handle item click here, e.g., navigate to another fragment based on the category
            val fragment = when (category) {
                "beginner" -> BeginnerFragment()
                "intermediate" -> IntermediateFragment()
                "advanced" -> AdvanceFragment()
                else -> null
            }
            fragment?.let {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameUser, it)
                    .addToBackStack(null)
                    .commit()
            }
        }
        recyclerView.adapter = adapter

        return rootView
    }
}