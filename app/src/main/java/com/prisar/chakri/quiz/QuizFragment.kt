package com.prisar.chakri.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.google.android.material.composethemeadapter.MdcTheme

class QuizFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                MdcTheme {
                    Quiz()
                }
            }
        }
    }
}

@Composable
private fun Quiz() {

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(600.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(10) {
            val count = remember { mutableStateOf(it + 1) }
            val questionStatement =
                "What is the answer. Its a long question? চাকরির খবর ও প্রস্তুতি"
            val optionA = "a)" + " is correct"
            val optionB = "b)" + " is correct"
            val optionC = "c)" + " is correct"
            val optionD = "d)" + " is correct"

            Card(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxWidth().height(100.dp)
            ) {
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
                Text(
                    text = optionA,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 0.dp)
                )

                Text(
                    text = optionB,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 50.dp)
                )

                Text(
                    text = optionC,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 100.dp)
                )

                Text(
                    text = optionD,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 150.dp)
                )
            }

            Divider(color = Color.Black)
        }
    }
}