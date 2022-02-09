package com.prisar.chakri.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.google.android.material.composethemeadapter.MdcTheme
import com.prisar.chakri.R

class QuizFragment : Fragment() {
    val Red = Color(0xffff0000)
    val Green = Color(red = 0f, green = 1f, blue = 0f)
    val White = Color(red = 0f, green = 0f, blue = 0f)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            val textView = findViewById<TextView>(R.id.volley_text)

            val vm = QuestionViewModel()
            setContent {
                MdcTheme {
                    QuizView(vm)
                }
            }
        }
    }
}

@Composable
fun QuizView(vm: QuestionViewModel) {
    LaunchedEffect(Unit, block = {
        vm.getQuestionList()
    })

    val Red = Color(0xffff0000)
    val Green = Color(red = 0f, green = 1f, blue = 0f)
    val White = Color(red = 1f, green = 1f, blue = 1f)

    val correctAnswers = remember { mutableStateOf(0) }

    val isDarkTheme = isSystemInDarkTheme()
    val bgColor = ansTextColor("UNANSWERED", isDarkTheme)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = vm.questionList.size.toString() + " questions, " + (if (correctAnswers.toString() == "0") "" else ((correctAnswers.value).toString() + " correct answers")))
                    }
                })
        },
        content = {
            if (vm.errorMessage.isEmpty()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        itemsIndexed(vm.questionList) { index, question ->
                            val questionNo = (index + 1).toString()
                            val answeredStatus = remember { mutableStateOf("UNANSWERED") }
                            val answerColor = remember { mutableStateOf(bgColor) }
                            val aIsSelected = remember { mutableStateOf(false) }
                            val bIsSelected = remember { mutableStateOf(false) }
                            val cIsSelected = remember { mutableStateOf(false) }
                            val dIsSelected = remember { mutableStateOf(false) }
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                    ) {
                                        Text(
                                            text = questionNo + ". " + question.description,
//                                            maxLines = 1,
                                            softWrap = true,
//                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.subtitle1
                                        )
                                    }
//                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Checkbox(checked = aIsSelected.value, onCheckedChange = { checked ->
                                        if (answeredStatus.value.toString() == "UNANSWERED") {
                                            if (checked) {
                                                answeredStatus.value =
                                                    if (question.correctOption == "A") "CORRECT" else "WRONG"
                                                correctAnswers.value = if (question.correctOption == "A") correctAnswers.value+1 else correctAnswers.value
                                                answerColor.value =
                                                    ansTextColor(answeredStatus.value.toString(), isDarkTheme)
                                            }
                                            aIsSelected.value = checked
                                        }
                                    })
                                    Text(text = question.optionA, modifier = Modifier.padding(vertical = 0.dp, horizontal = 16.dp))
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Checkbox(checked = bIsSelected.value, onCheckedChange = { checked ->
                                        if (answeredStatus.value.toString() == "UNANSWERED") {
                                            if (checked) {
                                                answeredStatus.value =
                                                    if (question.correctOption == "B") "CORRECT" else "WRONG"
                                                correctAnswers.value = if (question.correctOption == "B") correctAnswers.value+1 else correctAnswers.value
                                                answerColor.value =
                                                    ansTextColor(answeredStatus.value.toString(), isDarkTheme)
                                            }
                                            bIsSelected.value = checked
                                        }
                                    })
                                    Text(text = question.optionB, modifier = Modifier.padding(vertical = 0.dp, horizontal = 16.dp))
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Checkbox(checked = cIsSelected.value, onCheckedChange = { checked ->
                                        if (answeredStatus.value.toString() == "UNANSWERED") {
                                            if (checked) {
                                                answeredStatus.value =
                                                    if (question.correctOption == "C") "CORRECT" else "WRONG"
                                                correctAnswers.value = if (question.correctOption == "C") correctAnswers.value+1 else correctAnswers.value
                                                answerColor.value =
                                                    ansTextColor(answeredStatus.value.toString(), isDarkTheme)
                                            }
                                            cIsSelected.value = checked
                                        }
                                    })
                                    Text(text = question.optionC, modifier = Modifier.padding(vertical = 0.dp, horizontal = 16.dp))
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Checkbox(checked = dIsSelected.value, onCheckedChange = { checked ->
                                        if (answeredStatus.value.toString() == "UNANSWERED") {
                                            if (checked) {
                                                answeredStatus.value =
                                                    if (question.correctOption == "D") "CORRECT" else "WRONG"
                                                correctAnswers.value = if (question.correctOption == "D") correctAnswers.value+1 else correctAnswers.value
                                                answerColor.value =
                                                    ansTextColor(answeredStatus.value.toString(), isDarkTheme)
                                            }
                                            dIsSelected.value = checked
                                        }
                                    })
                                    Text(text = question.optionD, modifier = Modifier.padding(vertical = 0.dp, horizontal = 16.dp))
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Text(text = "Answer: " + question.correctOption, modifier = Modifier.padding(vertical = 5.dp, horizontal = 2.dp), color = answerColor.value)
                                }
                                Divider()
                            }
                        }
                    }
                }
            } else {
                Text(vm.errorMessage)
            }
        }
    )
}

fun ansTextColor(answeredStatus: String, isDarkTheme: Boolean): Color {
    val Red = Color(0xffff0000)
    val Green = Color(red = 0f, green = 1f, blue = 0f)
    val Hide = if (isDarkTheme) Color(red = 0f, green = 0f, blue = 0f) else Color(red = 1f, green = 1f, blue = 1f)
    return if (answeredStatus.toString() == "CORRECT") Green else (if (answeredStatus.toString() == "UNANSWERED") Hide else Red)
}
