package com.example.nanoleaftest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NanoCard(modifier: Modifier = Modifier, lightbulbAmount: String) {
    Card(modifier = modifier
        .size(80.dp)
        .background(Color.Cyan.copy(alpha = 0.5f))) {
        Column(modifier = modifier) {
            Text(text = "Light Bucket")
            Text(text = lightbulbAmount)
        }
    }
}