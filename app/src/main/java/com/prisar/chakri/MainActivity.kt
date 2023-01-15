package com.prisar.chakri

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prisar.chakri.ui.theme.ChakriTheme
import com.prisar.chakri.ui.theme.Teal200
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
fun CardWithMultipleViews() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        elevation = 10.dp,
        contentColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(10.dp)
            .background(color = Teal200)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val textToSpeech: TextToSpeech = TextToSpeech(LocalContext.current, TextToSpeech.OnInitListener {  })

            textToSpeech.language = Locale("en", "IN")


            Button(colors = buttonColors(backgroundColor = Teal200),
                onClick = { textToSpeech.speak("I", TextToSpeech.QUEUE_FLUSH,null) }) {
                Text(text = "I", style = TextStyle(fontSize = 24.sp))
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Learn English")

        CardWithMultipleViews()
    }

}