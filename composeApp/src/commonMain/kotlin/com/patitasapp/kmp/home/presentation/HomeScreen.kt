package com.patitasapp.kmp.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.patitasapp.kmp.home.presentation.components.EmptyPetsScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = remember { HomeViewModel() },
    onCreatePetClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    if (state.isEmpty) {
        EmptyPetsScreen(
            onCreatePetClick = onCreatePetClick
        )
    } else {
        // TODO: aquí iría tu grid/lista de mascotas cuando haya datos
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Lista de mascotas (WIP)")
        }
    }
}