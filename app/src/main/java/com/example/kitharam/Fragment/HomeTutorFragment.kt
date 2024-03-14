package com.example.kitharam.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        )
        val adapter = Adapter(data) { category ->
            val fragment = when (category) {
                "Beginner" -> BeginnerFragment()
                "Intermediate" -> IntermediateFragment()
                "Advanced" -> AdvanceFragment()
                else -> null
            }
            fragment?.let {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameTutor, it)
                    .addToBackStack(null)
                    .commit()
            }
        }
        recyclerView.adapter = adapter

        return rootView
    }
}