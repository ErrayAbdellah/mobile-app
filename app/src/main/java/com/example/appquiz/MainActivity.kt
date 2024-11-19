package com.example.appquiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appquiz.databinding.ActivityMainBinding
import com.example.appquiz.fragment.PrimaryFragment
import com.example.appquiz.fragment.StartFragment

class MainActivity : AppCompatActivity(), PrimaryFragment.OnPrimaryFragmentInteractionListener {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showPrimaryFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        showPrimaryFragment()

    }
    private fun showPrimaryFragment() {
        // Clear back stack
        supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)

        // Show PrimaryFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PrimaryFragment(),"START_FRAGMENT")
            .commit()
    }

    private fun showSecondaryFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, StartFragment(),"START_FRAGMENT")
            .addToBackStack(null) // Add transaction to back stack
            .commit()
    }


    override fun onStartClicked() {
        showSecondaryFragment()
    }

    override fun onResetClicked() {
        // Handle reset logic here (e.g., resetting scores)
    }



}