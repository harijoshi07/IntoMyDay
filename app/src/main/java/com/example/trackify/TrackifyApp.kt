package com.example.trackify

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trackify.ui.screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackifyApp(modifier: Modifier = Modifier) {

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
    }) {
        HomeScreen(modifier = Modifier.padding(it))

    }

}

@Preview
@Composable
private fun PrevTrackifyApp() {
    TrackifyApp()

}