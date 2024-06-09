package com.project.HerbalEase.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.HerbalEase.R
import com.project.HerbalEase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    android.util.Log.e("FTEST", "1")
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_home)
                }

                R.id.navigation_favorite -> {
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_favorite)
                }

                R.id.navigation_discuss -> {
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_discuss)
                }

                R.id.navigation_profile -> {
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_profile)
                }
            }
            false
        }

    }

}