package com.example.appquiz.activities

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appquiz.databinding.ActivityQuizBinding
import com.example.appquiz.data.repository.QuizRepository
import com.example.appquiz.data.repository.QuizViewModelFactory
import com.example.appquiz.data.repository.RetrofitInstance
import com.example.appquiz.domain.usecase.GetQuizQuestionsUseCase
import com.example.appquiz.ui.quiz.QuizViewModel
import kotlin.math.log

class QuizActivity : AppCompatActivity() {
    private lateinit var  binding :ActivityQuizBinding
    private val TAG = "QuizActivity"

    val viewModel: QuizViewModel by lazy {
        ViewModelProvider(
            this,
            QuizViewModelFactory(GetQuizQuestionsUseCase(QuizRepository(RetrofitInstance.apiService)))
        )[QuizViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG,"class QuizActivity !!")

        viewModel.question.observe(this) { questions ->

            if (questions != null) {
                // Handle successful data
               Log.d(TAG,"Question -> $questions")
            }
        }

        viewModel.error.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }



}