package com.prisar.chakri

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prisar.chakri.ui.theme.ChakriTheme
import com.prisar.chakri.ui.theme.Teal200
import kotlinx.coroutines.delay
import java.util.*
import kotlin.math.roundToInt

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

class CardItem(fromText: String, secondText: String) {
    val fromText = fromText
    val secondText = secondText
}

@Composable
fun CardWithMultipleViews() {
    var cardNumber by remember { mutableStateOf(0) }

    val cardItems = listOf(
        CardItem("আমি", "I"),
        CardItem("তুমি", "You"),
        CardItem("তারা", "They"),
        CardItem("ধন্যবাদ", "Thank you"),
        CardItem("তুমি কেমন আছো?", "How are you?"),
        CardItem("আমি বাড়ি যাচ্ছি", "I am going home")
    )

    val paddingModifier = Modifier.padding(10.dp)

    val context = LocalContext.current

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Card(
        elevation = 10.dp,
        contentColor = Color.White,
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .fillMaxWidth()
            .height(400.dp)
            .padding(10.dp)
            .background(color = Teal200)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val (x,y) = dragAmount
                    when {
                        x > 0 ->{
                            /* right */
                            if (x > 20) {
                                offsetX = 0.0F
                                cardNumber = if (cardNumber + 1 < cardItems.size) cardNumber+1 else 0
                            }
                        }
                        x < 0 ->{
                            /* left */
                            if (x < -20) {
                                offsetX = 0.0F
                                cardNumber = if (cardNumber + 1 < cardItems.size) cardNumber+1 else 0
                            }
                        }
                    }
                    when {
                        y > 0 -> { /* down */ offsetY = 0.0F }
                        y < 0 -> { /* up */ offsetY = 0.0F }
                    }

                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(cardItems[cardNumber].fromText, style = TextStyle(fontSize = 42.sp))

            val textToSpeech: TextToSpeech = TextToSpeech(LocalContext.current, TextToSpeech.OnInitListener {  })
            val utteranceProgressListener = object : UtteranceProgressListener() {
                override fun onStart(p0: String?) {
                    TODO("Not yet implemented")
                }

                override fun onDone(p0: String?) {
                    TODO("Not yet implemented")
                }

                override fun onError(p0: String?) {
                    TODO("Not yet implemented")
                }
            }

            textToSpeech.language = Locale("en", "IN")
            textToSpeech.setOnUtteranceProgressListener(utteranceProgressListener)

            Button(colors = buttonColors(backgroundColor = Teal200),
                modifier = Modifier.padding(16.dp),
                onClick = {
                    textToSpeech.speak(cardItems[cardNumber].secondText, TextToSpeech.QUEUE_FLUSH,null)

                }) {
                Text(text = cardItems[cardNumber].secondText, style = TextStyle(fontSize = 42.sp))
            }
            
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                cardNumber = if (cardNumber + 1 < cardItems.size) cardNumber+1 else 0
            }) {
                Text(text = ">")
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