package com.example.appquiz.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appquiz.domain.usecase.GetQuizQuestionsUseCase
import com.example.appquiz.ui.quiz.QuizViewModel

class QuizViewModelFactory(private val getQuizQuestionsUseCase: GetQuizQuestionsUseCase) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(getQuizQuestionsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}