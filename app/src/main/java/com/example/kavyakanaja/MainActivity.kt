package com.example.kavyakanaja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.kavyakanaja.presentation.navigation.BottomNavigationBar
import com.example.kavyakanaja.presentation.navigation.NavGraph
import com.example.kavyakanaja.ui.theme.KavyaKanajaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KavyaKanajaTheme {
                val navController = rememberNavController()
                
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { paddingValues ->
                    NavGraph(navController = navController, paddingValues = paddingValues)
                }
            }
        }
    }
}