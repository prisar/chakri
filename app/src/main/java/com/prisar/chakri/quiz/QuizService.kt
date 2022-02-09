package com.prisar.chakri.quiz

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://asia-south1-agrohikulik.cloudfunctions.net/"

interface QuizService {
    @GET("questionSets")
    suspend fun getQuestions(): QuestionSet

    companion object {
        var apiService: QuizService? = null
        fun getInstance(): QuizService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(QuizService::class.java)
            }
            return apiService!!
        }
    }
}