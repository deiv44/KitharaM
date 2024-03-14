package com.example.kitharam.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kitharam.R


class AdvanceFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_advance, container, false)
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val backButton = view.findViewById<Button>(R.id.backbtn3)

            backButton.setOnClickListener { requireActivity() }
        }

}