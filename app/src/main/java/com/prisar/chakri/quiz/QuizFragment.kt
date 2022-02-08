package com.prisar.chakri.quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.google.android.material.composethemeadapter.MdcTheme
import com.prisar.chakri.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.ViewModel

class QuizFragment : Fragment() {

    private lateinit var questions: List<Question>

    val apiService = QuizService.getInstance()

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Todos")
                    }
                })
        },
        content = {
            if (vm.errorMessage.isEmpty()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(vm.questionList) { question ->
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
                                            question.description,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Checkbox(checked = question.hasImage, onCheckedChange = null)
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

//        val queue = Volley.newRequestQueue(LocalContext.current)
//        val url = "https://asia-south1-agrohikulik.cloudfunctions.net/questionSets"
//
//        //    https://stackoverflow.com/a/68046929/3925626
//        val questions = remember {
//            mutableStateListOf<Question>()
//        }
//        val request = JsonObjectRequest(Request.Method.GET, url, null,
//            { response ->
//                try {
//                    Log.d("volley response", response.toString())
//                    val jsonArray = response.getJSONArray("questions")
//                    for (i in 0 until jsonArray.length()) {
//                        val question = jsonArray.getJSONObject(i)
//                        val type = question.getString("type")
//                        val description = question.getString("description")
//                        val optionA = question.getString("optionA")
//                        val optionB = question.getString("optionB")
//                        val optionC = question.getString("optionC")
//                        val optionD = question.getString("optionD")
//                        val correctOption = question.getString("correctOption")
//                        val hasImage = question.getBoolean("hasImage")
//
//                        val questionObj = Question(type, description, imageUrl = "", optionA, optionB, optionC, optionD, correctOption, hasImage = false)
//                        questions.add(questionObj)
//                        Log.d("json parse", question.toString())
//                    }
//
////                questions = listOf(jsonArray).map { q -> Question(q.getString("type")) }
//                } catch (e: JSONException) {
//                    Log.d("parsing", e.toString())
//                }
//            },
//            { error ->
////            textView.text = "That didn't work!" + error.toString()
//                Log.e("volley error", error.toString())
//            })
//
//        queue.add(request)
}

//fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
//    clear()
//    addAll(newList)
//}
