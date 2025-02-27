package com.example.kitharam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kitharam.Fragment.ChatUserFragment
import com.example.kitharam.Fragment.HomeUserFragment
import com.example.kitharam.Fragment.ProfileUserFragment
import com.example.kitharam.Fragment.ToolUserFragment
import com.example.kitharam.databinding.ActivityUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeUserFragment())


        binding.bottomNavigationuser.setOnItemSelectedListener {


                when(it.itemId){
                    R.id.sthome->replaceFragment (HomeUserFragment())
                    R.id.sttool->replaceFragment (ToolUserFragment())
                    R.id.stprofile->replaceFragment (ProfileUserFragment())

                    else -> {

                }
            }
            true
    }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameUser, fragment)
        fragmentTransaction.commit()
    }
}