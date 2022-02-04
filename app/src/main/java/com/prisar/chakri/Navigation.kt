package com.prisar.chakri

import androidx.fragment.app.Fragment
import java.security.InvalidParameterException
import androidx.navigation.fragment.findNavController

enum class Screen { Welcome, SignUp, SignIn, Quiz }

fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
//        Screen.Welcome -> {
//            findNavController().navigate(R.id.welcome_fragment)
//        }
//        Screen.SignUp -> {
//            findNavController().navigate(R.id.sign_up_fragment)
//        }
//        Screen.SignIn -> {
//            findNavController().navigate(R.id.sign_in_fragment)
//        }
        Screen.Quiz -> {
            findNavController().navigate(R.id.quiz_fragment)
        }
    }
}
