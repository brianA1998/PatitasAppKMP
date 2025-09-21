package com.patitasapp.kmp.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun BottomBar(onAction: (index: Int) -> Unit = {}) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(72.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // left icon
            Text(
                text = "🏠",
                modifier = Modifier.clickable { onAction(0) }
            )

            Text(text = "🐾", modifier = Modifier.clickable { onAction(1) })

            // center big + button
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+", color = Color.White)
            }

            Text(text = "🔔", modifier = Modifier.clickable { onAction(3) })

            Text(text = "👤", modifier = Modifier.clickable { onAction(4) })
        }
    }
}