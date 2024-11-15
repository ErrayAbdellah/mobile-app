package com.example.appquiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appquiz.databinding.ActivityMainBinding
import com.example.appquiz.fragment.StartFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtnId.setOnClickListener {
            showFragment(StartFragment())
        }
        binding.resetScoreBtnId.setOnClickListener {

        }

    }
    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Make sure you have a container with this ID in your layout
            .addToBackStack(null) // Adds the transaction to the back stack so the user can navigate back
            .commit()
    }
}