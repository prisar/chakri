package com.prisar.chakri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
//import com.prisar.chakri.quiz.QuizScreen
import com.prisar.chakri.ui.theme.ChakriTheme

//import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.ui.setupWithNavController
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.android.material.navigation.NavigationView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChakriTheme {
                Greeting("Android")
            }
        }

//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.welcome_fragment,
//            R.id.quiz_fragment))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

//        QuizScreen()

    }
}

@Composable
fun Greeting(name: String) {
//    Scaffold { paddingValues: PaddingValues ->
//        AppNavGraph(modifier = Modifier.padding(paddingValues))
//    }
}