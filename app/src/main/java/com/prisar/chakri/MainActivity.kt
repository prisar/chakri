package com.prisar.chakri

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prisar.chakri.ui.theme.Black
import com.prisar.chakri.ui.theme.ChakriTheme
import com.prisar.chakri.ui.theme.Teal200
import com.prisar.chakri.ui.theme.Yellow
import com.prisar.chakri.ui.theme.SkyBlue
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
fun Greeting(name: String) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Teal200),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp),
            onClick = {
                context.startActivity(
                    Intent(context, KannadaActivity::class.java)
                )

            }) {
            Text(text = "kannada", style = TextStyle(fontSize = 32.sp))
        }

        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Teal200),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp),
            onClick = {
                context.startActivity(
                    Intent(context, EnglishActivity::class.java)
                )

            }) {
            Text(text = "english", style = TextStyle(fontSize = 32.sp))
        }

        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Teal200),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp),
            onClick = {
                context.startActivity(
                    Intent(context, HindiActivity::class.java)
                )

            }) {
            Text(text = "hindi", style = TextStyle(fontSize = 32.sp))
        }

        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Teal200),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp),
            onClick = {
                context.startActivity(
                    Intent(context, MalayalamActivity::class.java)
                )

            }) {
            Text(text = "Malayalam", style = TextStyle(fontSize = 32.sp))
        }
    }
}