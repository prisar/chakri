package com.prisar.chakri

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.prisar.chakri.ui.theme.*
import java.util.*
import kotlin.math.roundToInt

class EnglishActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChakriTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting3("Android")
                }
            }
        }
    }
}


@Composable
fun CardWithMultipleViews() {
    var cardNumber by remember { mutableStateOf(0) }
    val shape = CircleShape

    val cardItems = listOf(
        CardItem("আমি", "I"),
        CardItem("তুমি", "You"),
        CardItem("তারা", "They"),
        CardItem("ধন্যবাদ", "Thank you"),
        CardItem("তুমি কেমন আছো?", "How are you?"),
        CardItem("আমি বাড়ি যাচ্ছি", "I am going home"),
        CardItem("তুমি কি করছো", "What are you doing"),
        CardItem("তোমার নাম কি", "What is your name"),
        CardItem("তুমি কি করো ", "What do you do"),
        CardItem("সে ব্যাঙ্গালোরে থাকে", "He lives in Bangalore"),
    )

    val paddingModifier = Modifier.padding(10.dp)

    val context = LocalContext.current

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Card(
        elevation = 10.dp,
        contentColor = Color.Black,
        backgroundColor = SkyBlue,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .fillMaxWidth()
            .height(400.dp)
            .padding(30.dp)
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

//                    offsetX += dragAmount.x
//                    offsetY += dragAmount.y
                }
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(cardItems[cardNumber].fromText, style = TextStyle(fontSize = 24.sp))

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

            textToSpeech.language = Locale("en", "IN")
            textToSpeech.setOnUtteranceProgressListener(utteranceProgressListener)

            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Teal200),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
                    .padding(30.dp),
                onClick = {
                    textToSpeech.speak(
                        cardItems[cardNumber].secondText,
                        TextToSpeech.QUEUE_FLUSH,
                        null
                    )

                }) {
                Text(text = cardItems[cardNumber].secondText, style = TextStyle(fontSize = 32.sp))
            }

            Text(text = "Next >>",
                style = TextStyle(
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(200.dp)
                    .padding(16.dp)
//                    .border(2.dp, Black, shape)
                    .background(Yellow, shape)
                    .padding(16.dp)
                    .clickable {
                        cardNumber = if (cardNumber + 1 < cardItems.size) cardNumber + 1 else 0
                    })

        }
    }

    Text(text = "kannada",
        style = TextStyle(
            color = Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Gray,
                        SkyBlue)
                ), shape)
            .padding(16.dp)
            .clickable {
                context.startActivity(
                    Intent(context, KannadaActivity::class.java)
                )
            })
}

@Composable
fun Greeting3(name: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Learn English",
            style = TextStyle(
//                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            ),
        )

        CardWithMultipleViews()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ChakriTheme {
        Greeting3("Android")
    }
}