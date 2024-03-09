package com.example.kitharam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kitharam.Fragment.ChatTutorFragment
import com.example.kitharam.Fragment.HomeTutorFragment
import com.example.kitharam.Fragment.HomeUserFragment
import com.example.kitharam.Fragment.ProfileTutorFragment
import com.example.kitharam.Fragment.ToolTutorFragment
import com.example.kitharam.databinding.ActivityTutorBinding


class TutorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeTutorFragment())


        binding.bottomNavigationuser.setOnItemSelectedListener {
            when(it.itemId){
                R.id.trhome->replaceFragment (HomeTutorFragment())
                R.id.trtool->replaceFragment (ToolTutorFragment())
                R.id.trchat->replaceFragment (ChatTutorFragment())
                R.id.trprofile->replaceFragment (ProfileTutorFragment())

                else -> {

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameTutor, fragment)
        fragmentTransaction.commit()
    }
}