package com.example.kitharam.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kitharam.R

class BeginnerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_beginner, container, false)

        val btnBack: Button = rootView.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return rootView
    }
}
