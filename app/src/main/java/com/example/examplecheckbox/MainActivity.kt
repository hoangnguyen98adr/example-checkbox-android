package com.example.examplecheckbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.examplecheckbox.screens.HomeScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction().apply {
            val bundle = bundleOf("TAG" to 0)
            add(R.id.navHostFragment, HomeScreen::class.java, bundle)
            setReorderingAllowed(true)
            addToBackStack("")
            commit()
        }
    }
}