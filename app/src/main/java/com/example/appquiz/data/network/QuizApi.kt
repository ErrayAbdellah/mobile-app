package com.example.appquiz.data.network

import com.example.appquiz.model.Quiz
import retrofit2.Response
import retrofit2.http.GET

interface QuizApi {
    @GET("quiz")
    suspend fun getQuiz() :Response<List<Quiz>>
}