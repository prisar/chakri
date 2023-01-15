package com.prisar.chakri

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.prisar.chakri.ui.theme.ChakriTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChakriTheme {
                Greeting("Android")
            }
        }

//        QuizScreen()

    }
}

@Composable
fun Greeting(name: String) {
    Text("Chakri")

    val textToSpeech: TextToSpeech = TextToSpeech(LocalContext.current, TextToSpeech.OnInitListener {  })

    textToSpeech.setLanguage(Locale("bn_IN"))

    Button(onClick = { textToSpeech.speak("তোমার বাড়ি কোথায়", TextToSpeech.QUEUE_FLUSH,null) }) {
        Text(text = "speak")
    }
}