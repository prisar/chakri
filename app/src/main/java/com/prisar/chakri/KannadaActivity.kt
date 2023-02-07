package com.prisar.chakri

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prisar.chakri.ui.theme.Black
import com.prisar.chakri.ui.theme.ChakriTheme
import com.prisar.chakri.ui.theme.Teal200
import com.prisar.chakri.ui.theme.Yellow
import java.util.*
import kotlin.math.roundToInt

class KannadaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChakriTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun KannadaCards() {
    var cardNumber by remember { mutableStateOf(0) }
    val shape = CircleShape

    val cardItems = listOf(
        CardItem("আমি", "ನಾನು"),
        CardItem("তুমি", "ನೀವು"),
        CardItem("তারা", "ಅವರು"),
        CardItem("ধন্যবাদ", "ಧನ್ಯವಾದ"),
        CardItem("তুমি কেমন আছো?", "ನೀವು ಹೇಗಿದ್ದೀರಿ?"),
        CardItem("আমি বাড়ি যাচ্ছি", "ನಾನು ಮನೆಗೆ ಹೋಗುತ್ತೇನೆ"),
        CardItem("তুমি কি করছো", "ನೀನು ಏನು ಮಾಡುತ್ತಿರುವೆ"),
        CardItem("তোমার নাম কি", "ನಿನ್ನ ಹೆಸರೇನು"),
        CardItem("তুমি কি করো ", "ನೀವೇನು ಮಾಡುವಿರಿ"),
        CardItem("সে ব্যাঙ্গালোরে থাকে", "ಅವನು ಬೆಂಗಳೂರಿನಲ್ಲಿ ವಾಸಿಸುತ್ತಾನೆ"),
        CardItem("i will go to indiranagar", "ನಾನು ಇಂದಿರಾನಗರಕ್ಕೆ ಹೋಗುತ್ತೇನೆ"),
        CardItem("tomorrow i have an exam", "ನಾಳೆ ನನಗೆ ಪರೀಕ್ಷೆ ಇದೆ"),
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

                    val (x, y) = dragAmount
                    when {
                        x > 0 -> {
                            /* right */
                            if (x > 20) {
                                offsetX = 0.0F
                                cardNumber =
                                    if (cardNumber + 1 < cardItems.size) cardNumber + 1 else 0
                            }
                        }
                        x < 0 -> {
                            /* left */
                            if (x < -20) {
                                offsetX = 0.0F
                                cardNumber =
                                    if (cardNumber + 1 < cardItems.size) cardNumber + 1 else 0
                            }
                        }
                    }
                    when {
                        y > 0 -> { /* down */ offsetY = 0.0F
                        }
                        y < 0 -> { /* up */ offsetY = 0.0F
                        }
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

            val textToSpeech: TextToSpeech =
                TextToSpeech(LocalContext.current, TextToSpeech.OnInitListener { })
            val utteranceProgressListener = object : UtteranceProgressListener() {
                override fun onStart(p0: String?) {
                    TODO("Not yet implemented")
                }

                override fun onDone(p0: String?) {
                    TODO("Not yet implemented")
                }

                @Deprecated("Deprecated in Java")
                override fun onError(p0: String?) {
                    TODO("Not yet implemented")
                }
            }

            textToSpeech.language = Locale("kn", "IN")
            textToSpeech.setOnUtteranceProgressListener(utteranceProgressListener)

            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Teal200),
                modifier = Modifier.padding(16.dp),
                onClick = {
                    textToSpeech.speak(
                        cardItems[cardNumber].secondText,
                        TextToSpeech.QUEUE_FLUSH,
                        null
                    )

                }) {
                Text(text = cardItems[cardNumber].secondText, style = TextStyle(fontSize = 42.sp))
            }

            Text(text = "Next >>",
                style = TextStyle(
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(2.dp, Black, shape)
                    .background(Yellow, shape)
                    .padding(16.dp)
                    .clickable {
                        cardNumber = if (cardNumber + 1 < cardItems.size) cardNumber + 1 else 0
                    })

        }
    }
}

@Composable
fun Greeting2(name: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Learn Kannada")

        KannadaCards()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChakriTheme {
        Greeting2("Android")
    }
}