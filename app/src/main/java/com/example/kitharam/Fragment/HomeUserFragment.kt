package com.example.kitharam.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.kitharam.R

// TODO: Rename parameter arguments, choose names that match

class HomeUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_user, container, false)
        val beginButton =  view.findViewById<Button>(R.id.btnbeg)
        val intermediateButton =  view.findViewById<Button>(R.id.btninter)
        val advanceButton =  view.findViewById<Button>(R.id.btnadv)

        beginButton.setOnClickListener{
            val intent = Intent(activity, BeginnerFragment::class.java)
            startActivity(intent)
        }

        intermediateButton.setOnClickListener{
            val intent = Intent(activity, IntermediateFragment::class.java)
            startActivity(intent)
        }

        advanceButton.setOnClickListener{
            val intent = Intent(activity, AdvanceFragment::class.java)
            startActivity(intent)
        }
        return view
    }
}