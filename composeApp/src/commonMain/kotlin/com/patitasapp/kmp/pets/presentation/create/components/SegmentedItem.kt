package com.patitasapp.kmp.pets.presentation.create.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
private fun SegmentedItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    violet: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        tonalElevation = if (selected) 2.dp else 0.dp,
        shadowElevation = if (selected) 1.dp else 0.dp,
        shape = RoundedCornerShape(16.dp),
        color = if (selected) violet else Color(0xFFFFF3E9),
        contentColor = if (selected) Color.White else textColor,
        modifier = modifier
            .height(44.dp)
            .clickable { onClick() }
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}