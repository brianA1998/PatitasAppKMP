package com.patitasapp.kmp.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patitasapp.kmp.home.domain.Pet
import com.patitasapp.kmp.home.presentation.components.PetCard

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = remember { HomeViewModel() },
    onPetClick: (Pet) -> Unit = {},
    onBottomAction: (index: Int) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    // Altura visual fija de la bottom bar (la misma que usamos más abajo)
    val bottomBarHeight = 72.dp

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val screenHeight = maxHeight

        // --- Overlap dinámico: cuánto "sube" el FAB encima de la barra.
        // En pantallas pequeñas lo subimos menos (para que quede más abajo),
        // en pantallas grandes un poco más.
        val overlap = when {
            screenHeight < 700.dp -> 8.dp   // pantallas chicas (ej. algunos iPhones)
            screenHeight < 850.dp -> 12.dp  // pantallas medianas (phones comunes)
            else -> 16.dp                    // pantallas grandes (tablets / phablets)
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Adopción mascota", style = MaterialTheme.typography.titleMedium) }
                )
            },
            bottomBar = {
                // Bottom bar con navigationBarsPadding() para respetar home indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .height(bottomBarHeight)
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { onBottomAction(0) }) { Text("🏠") }
                            IconButton(onClick = { onBottomAction(1) }) { Text("🐾") }
                        }

                        Spacer(modifier = Modifier.width(64.dp)) // hueco para FAB

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { onBottomAction(3) }) { Text("🔔") }
                            IconButton(onClick = { onBottomAction(4) }) { Text("👤") }
                        }
                    }

                    // FAB centrado y con offset dinámico (menor overflow en pantallas chicas)
                    FloatingActionButton(
                        onClick = { onBottomAction(2) },
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = -overlap), // <- aquí controlamos cuánto "flota"
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        shape = CircleShape,
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
                    ) {
                        Text("+", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        ) { innerPadding ->
            // --- Ajustamos el padding inferior del grid para que ITEMS no queden debajo del FAB
            // Añadimos overlap + un extra (hitBuffer) para evitar toques accidentales al scrollear.
            val hitBuffer = 8.dp
            val gridBottomPadding = innerPadding.calculateBottomPadding() + overlap + hitBuffer + 12.dp

            val gridContentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = gridBottomPadding,
                start = 12.dp,
                end = 12.dp
            )

            val pets = state.pets
            val gridState = rememberLazyGridState()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = gridState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = gridContentPadding,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(pets, key = { it.id }) { pet ->
                    PetCard(pet = pet, onClick = { onPetClick(pet) })
                }
            }
        }
    }
}