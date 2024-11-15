package com.example.appquiz.ui.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appquiz.domain.usecase.GetQuizQuestionsUseCase
import com.example.appquiz.model.Quiz
import kotlinx.coroutines.launch

class QuizViewModel(private val getQuizQuestionsUseCase: GetQuizQuestionsUseCase) :ViewModel() {
    private val _question = MutableLiveData<List<Quiz>>()
    val question: LiveData<List<Quiz>> get() = _question

    private val _error = MutableLiveData<String>()
    public val error: LiveData<String> get() = _error

    fun loadQuizQuestion(){
        Log.d("QuizViewModel", "loadQuizQuestion called") // Debug log
        viewModelScope.launch {
            val result = getQuizQuestionsUseCase()
            if (result != null) {
                Log.d("QuizViewModel", "Questions fetched: $result") // Debug log
                _question.postValue(result)
            } else {
                Log.d("QuizViewModel", "Failed to fetch questions") // Debug log
                _error.postValue("Failed to load quiz questions")
            }
        }
    }

}