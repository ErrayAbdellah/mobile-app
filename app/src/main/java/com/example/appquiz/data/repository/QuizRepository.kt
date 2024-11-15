package com.example.appquiz.data.repository

import com.example.appquiz.model.Quiz
import com.example.appquiz.data.network.QuizApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizRepository(private val apiService: QuizApi) {
    suspend fun fetchQuizQuestions() :List<Quiz>? {
        return withContext(Dispatchers.IO){
            var response = apiService.getQuiz()
            if (response.isSuccessful){
                response.body()
            }else {
                null
            }
        }
    }
}