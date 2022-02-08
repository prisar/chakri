package com.prisar.chakri.quiz

import androidx.compose.runtime.Immutable

@Immutable
data class QuestionSet(
    val questions: List<Question>
)