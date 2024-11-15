package com.example.appquiz.domain.usecase

import com.example.appquiz.model.Quiz
import com.example.appquiz.data.repository.QuizRepository

class GetQuizQuestionsUseCase(private val repository: QuizRepository) {
    suspend operator fun invoke() : List<Quiz>? {
        return repository.fetchQuizQuestions()
    }
}