package com.prisar.chakri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.material.composethemeadapter.MdcTheme
import java.time.format.TextStyle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val greeting = findViewById<ComposeView>(R.id.greeting)
        greeting.setContent {
            MdcTheme {
                Greeting()
            }
        }
    }
}

@Composable
private fun Greeting() {

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(600.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        repeat(10) {
            val count = remember { mutableStateOf(it+1) }
            val questionStatement = "What is the answer. Its a long question? চাকরির খবর ও প্রস্তুতি"
            val optionA = "a)" + " is correct"
            val optionB = "b)" + " is correct"
            val optionC = "c)" + " is correct"
            val optionD = "d)" + " is correct"

            Card(backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxWidth().height(100.dp)) {
                Text(
                    text = count.value.toString() + ".",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(2.dp),
//                    modifier = Modifier.clickable { count.value += 1 },
                )

                Text(
                    text = questionStatement,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 40.dp, vertical = 2.dp)
                )
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Text(text = optionA,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 0.dp))

                Text(text = optionB,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 50.dp))

                Text(text = optionC,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 100.dp))

                Text(text = optionD,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 150.dp))
            }

            Divider(color = Color.Black)
        }
    }
}