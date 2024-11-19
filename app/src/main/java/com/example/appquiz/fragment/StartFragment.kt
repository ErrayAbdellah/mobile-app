package com.example.appquiz.fragment

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.appquiz.R
import com.example.appquiz.activities.QuizActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StartFragment : Fragment() {

    private lateinit var countdownText: TextView
    private var countdownTimer: CountDownTimer? = null // Store reference to the timer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        countdownText = view.findViewById(R.id.countdownText)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCountdownTimer()
    }

    private fun startCountdownTimer() {
        countdownTimer = object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdownText.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                // Navigate to QuizActivity
                val intent = Intent(requireContext(), QuizActivity::class.java)
                startActivity(intent)

                // Remove this fragment
                parentFragmentManager.beginTransaction()
                    .remove(this@StartFragment)
                    .commit()
            }
        }
        countdownTimer?.start() // Start the timer
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Cancel the timer to avoid memory leaks
        countdownTimer?.cancel()
    }
}