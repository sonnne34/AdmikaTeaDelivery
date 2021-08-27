package com.newAdmilaTea.newadmilatea

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.newAdmilaTea.newadmilatea.databinding.ActivityMainBinding
import com.newAdmilaTea.newadmilatea.ui.checkfragment.CheckFragment
import com.newAdmilaTea.newadmilatea.ui.controlCheckFragment.ControlCheckFragment
import com.newAdmilaTea.newadmilatea.ui.menufragment.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

         navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each

      
        navView.setupWithNavController(navController)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main,MenuFragment())
            .commit()


        navView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.menufragment -> {
//                    Toast.makeText(this, "Вы уже в меню", Toast.LENGTH_SHORT).show()
                }
                R.id.check_fragment-> {

                }
                R.id.report_fragment->{}
            }
        }


        navView.setOnNavigationItemSelectedListener{item ->
            when (item.itemId) {
                R.id.menufragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,MenuFragment())
                        .commit()
                }
                R.id.check_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,CheckFragment())
                        .commit()
                }
                R.id.report_fragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,CheckFragment())
                        .commit()
                }
            }
            true
        }
    }

    fun toGOcontolCheckFragment(){
               supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, ControlCheckFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}