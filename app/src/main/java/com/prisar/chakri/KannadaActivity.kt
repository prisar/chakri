package com.prisar.chakri

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prisar.chakri.ui.theme.*
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
                    KannadaView("Android")
                }
            }
        }
    }
}


val flashCards: List<FlashCard> = listOf<FlashCard>(
    FlashCard("আমি", "I", "ನಾನು", "", ""),
    FlashCard("তুমি", "you", "ನೀವು", "", ""), // you
    FlashCard("তারা", "they", "ಅವರು", "", ""), // they
    FlashCard("ধন্যবাদ", "thank you", "ಧನ್ಯವಾದ", "", ""), // thank you
    FlashCard("তুমি কেমন আছো?", "how are you?", "ನೀವು ಹೇಗಿದ್ದೀರಿ?", "", ""), // how are you?
    FlashCard("আমি বাড়ি যাচ্ছি", "i am going home", "ನಾನು ಮನೆಗೆ ಹೋಗುತ್ತೇನೆ", "", ""), // i am going home
    FlashCard("তুমি কি করছো", "what are you doing", "ನೀನು ಏನು ಮಾಡುತ್ತಿರುವೆ", "", ""), // what are you doing
)

@Composable
fun KannadaCards() {
    var cardNumber by remember { mutableStateOf(0) }
    val shape = CircleShape

    val cardItems = listOf(
        CardItem("আমি", "ನಾನು"), // I
        CardItem("তুমি", "ನೀವು"), // you
        CardItem("তারা", "ಅವರು"), // they
        CardItem("ধন্যবাদ", "ಧನ್ಯವಾದ"), // thank you
        CardItem("তুমি কেমন আছো?", "ನೀವು ಹೇಗಿದ್ದೀರಿ?"), // how are you?
        CardItem("আমি বাড়ি যাচ্ছি", "ನಾನು ಮನೆಗೆ ಹೋಗುತ್ತೇನೆ"), // i am going home
        CardItem("তুমি কি করছো", "ನೀನು ಏನು ಮಾಡುತ್ತಿರುವೆ"), // what are you doing
        CardItem("তোমার নাম কি", "ನಿನ್ನ ಹೆಸರೇನು"), // what is your name?
        CardItem("তুমি কি করো ", "ನೀವೇನು ಮಾಡುವಿರಿ"), // what do you do?
        CardItem("সে ব্যাঙ্গালোরে থাকে", "ಅವನು ಬೆಂಗಳೂರಿನಲ್ಲಿ ವಾಸಿಸುತ್ತಾನೆ"), // he lives in bangalore
        CardItem("আমি ইন্দিরানগর যাব", "ನಾನು ಇಂದಿರಾನಗರಕ್ಕೆ ಹೋಗುತ್ತೇನೆ"), // i will go to indiranagar
        CardItem("আগামীকাল আমার পরীক্ষা আছে", "ನಾಳೆ ನನಗೆ ಪರೀಕ್ಷೆ ಇದೆ"), // tomorrow i have an exam
        CardItem("আমি ডাক্তারের কাছে যাব", "ನಾನು ವೈದ್ಯರ ಬಳಿಗೆ ಹೋಗುತ್ತೇನೆ"), // I will go to the doctor
        CardItem("আপনার বয়স কত", "ನಿನ್ನ ವಯಸ್ಸು ಎಷ್ಟು"),  // how old are you
        CardItem("তুমি কোথায় যাবে", "ನೀವು ಎಲ್ಲಿಗೆ ಹೋಗುತ್ತೀರಿ"), // where will you go
        CardItem("কখন ট্রেন আসবে", "ರೈಲು ಯಾವಾಗ ಬರುತ್ತದೆ"), // when will the train come
        CardItem("আমি কন্নড় শিখছি", "ನಾನು ಕನ್ನಡ ಕಲಿಯುತ್ತಿದ್ದೇನೆ"), // I am learning kannada
        CardItem("how much?", "ಎಷ್ಟು?"), // how much?
        CardItem("where", "ಎಲ್ಲಿ"), // elli
        CardItem("five hundred", "ಐದು ನೂರು"), // aidu nuru
        CardItem("twenty five", "ಇಪ್ಪತ್ತೈದು"), // Ippattaidu
        CardItem("drink", "ಕುಡಿಯಿರಿ"), // Kuḍiyiri
        CardItem("read", "ಓದಿದೆ"), // Ōdide
        CardItem("arrive", "ತಲುಪು"),
        CardItem("can i sit here", "ನಾನು ಇಲ್ಲಿ ಕುಳಿತುಕೊಳ್ಳಬಹುದೇ"), //Nānu illi kuḷitukoḷḷabahudē
        CardItem("See you later", "ಆಮೇಲೆ ಸಿಗೋಣ") // Āmēle sigōṇa
        // what are you eating?
        // how are you today?
        // are you in office?
        // when will you come from office?
        // which food do you like?
        // when are you going home?
        // morning
        // evening
        // afternoon
        // night
        // work
        // i am busy
        // it is raining
        // do you have umbrella?
        // let's go home
        // do you want to buy anything?
        // where are you going?
        // you are looking pretty
        // when will you go to office?
        // are you hungry?
        // happy birthday
        // i am thirsty
        // can you bring me some water?
        // i like playing chess
        // what is your hobby?
        // are you married?
        // are you a student?
        // when can you come?
        // i am in bus
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
//                    .border(2.dp, Black, shape)
                    .background(Yellow, shape)
                    .padding(16.dp)
                    .clickable {
                        cardNumber = if (cardNumber + 1 < cardItems.size) cardNumber + 1 else 0
                    })

        }
    }
}

@Composable
fun KannadaView(name: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Learn Kannada",
            style = TextStyle(
//                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            ),
        )

        KannadaCards()
    }
}
