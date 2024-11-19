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
        viewModelScope.launch {
            try {
                val result = getQuizQuestionsUseCase()
                if (!result.isNullOrEmpty()) {
                    _question.postValue(result)
                } else {
                    _error.postValue("No quiz questions found.")
                }
            } catch (e: Exception) {
                Log.e("QuizViewModel", "Error fetching questions", e)
                _error.postValue("An error occurred: ${e.message}")
            }
        }

    }

}