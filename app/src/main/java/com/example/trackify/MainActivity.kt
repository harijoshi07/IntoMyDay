package com.example.trackify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.trackify.ui.theme.TrackifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrackifyTheme(
                darkTheme = true,
                dynamicColor = false
            ) {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    TrackifyApp(modifier = Modifier.padding(innerPadding))
//                }
                TrackifyApp()
            }
        }
    }
}

