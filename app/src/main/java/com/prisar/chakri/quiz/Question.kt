package com.prisar.chakri.quiz

import androidx.compose.runtime.Immutable

@Immutable
data class Question(
    val type: String,
    val description: String,
    val imageUrl: String?,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctOption: String,
    val hasImage: Boolean,
)
