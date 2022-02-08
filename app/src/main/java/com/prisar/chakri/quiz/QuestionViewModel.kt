package com.prisar.chakri.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuestionViewModel : ViewModel() {
    private val _questionList = mutableStateListOf<Question>()
    var errorMessage: String by mutableStateOf("")
    val questionList: List<Question>
        get() = _questionList

    fun getQuestionList() {
        viewModelScope.launch {
            val apiService = QuizService.getInstance()
            try {
                _questionList.clear()
                _questionList.addAll(apiService.getQuestions().questions)

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}