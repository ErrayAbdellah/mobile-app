package com.example.appquiz.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appquiz.adapter.QuizAdapter
import com.example.appquiz.databinding.ActivityQuizBinding
import com.example.appquiz.data.repository.QuizRepository
import com.example.appquiz.data.repository.QuizViewModelFactory
import com.example.appquiz.data.repository.RetrofitInstance
import com.example.appquiz.domain.usecase.GetQuizQuestionsUseCase
import com.example.appquiz.model.Quiz
import com.example.appquiz.ui.quiz.QuizViewModel

class QuizActivity : AppCompatActivity() {

    private lateinit var  binding :ActivityQuizBinding
    private val TAG = "QuizActivity"
    private lateinit var userAnswers: MutableMap<Int, String>

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

        binding.quizRecyclerView.layoutManager = LinearLayoutManager(this)
        userAnswers = mutableMapOf()
        viewModel.loadQuizQuestion()




        viewModel.question.observe(this) { questions ->
            if (!questions.isNullOrEmpty()) {
                Log.d(TAG, "Questions -> $questions")

                binding.quizRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.quizRecyclerView.adapter = QuizAdapter(
                    quizList = questions,
                    onAnswerSelected = { position, answer ->
                        // Handle the selected answer
                        userAnswers[position] = answer
                    },
                    onSubmitClicked = {
                        // Handle the submit button click
                        calculateResults(questions)
                    }
                )

            }
        }

        viewModel.error.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun calculateResults(questions: List<Quiz>) {
        var correctCount = 0
        var incorrectCount = 0

        questions.forEachIndexed { index, quiz ->
            val userAnswer = userAnswers[index]
            if (userAnswer == quiz.answer) {
                correctCount++
            } else {
                incorrectCount++
            }
        }

        // Show results
        Toast.makeText(this, "Correct: $correctCount, Incorrect: $incorrectCount", Toast.LENGTH_LONG).show()
    }


}