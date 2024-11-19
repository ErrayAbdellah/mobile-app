package com.example.appquiz.data.repository

import com.example.appquiz.data.network.QuizApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

//    private const val BASE_URL = BuildConfig.BASE_URL
    private const val BASE_URL = "http://10.0.2.2:3000/api/"

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: QuizApi by lazy {
        retrofit.create(QuizApi::class.java)
    }

}