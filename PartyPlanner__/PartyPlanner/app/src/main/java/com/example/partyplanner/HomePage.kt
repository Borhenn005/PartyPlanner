package com.example.partyplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.partyplanner.homeFragment.GuestsFragment
import com.example.partyplanner.homeFragment.HomeFragment
import com.example.partyplanner.homeFragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    private lateinit var bottom_navigation : BottomNavigationView
 //   private lateinit var logout : ImageButton

    private val homeFragment = HomeFragment()
    private val eventFragment = GuestsFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        replaceFragment(homeFragment)

//        logout = findViewById(R.id.ic_logoutt)
//
//        logout.setOnClickListener{
//            val intLog = Intent(this,login::class.java)
//            startActivity(intLog)
//            finish()
//        }

        bottom_navigation = findViewById(R.id.bottom_navigation)

        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_guests -> replaceFragment(eventFragment)
                else ->  replaceFragment(profileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()

        }
    }
}