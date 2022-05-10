package com.example.kotlin_starbucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_starbucks.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        setupNav()
    }

    private fun setupNav() {
        val navController = supportFragmentManager.findFragmentById(R.id.nav_main_event_fragment)?.findNavController()
        navController?.let {
            binding.bottomNavigationView.setupWithNavController(it)
        }

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainEventFragment -> hideBottomNav()
                R.id.whatsNewFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }


}