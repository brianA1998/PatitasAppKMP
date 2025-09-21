package com.patitasapp.kmp.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Adopción mascota", style = MaterialTheme.typography.titleMedium) }
            )
        },
        // --------------- important: use Scaffold FAB instead of manual offset ---------------
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onBottomAction(2) },
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Text(text = "+", style = MaterialTheme.typography.titleLarge)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        // you can change to Docked if your material version supports it; center is fine
        bottomBar = {
            // Respect system navigation bars (home indicator) so the bar is never cut
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding() // <-- ensures the nav bar sits above system nav/home indicator
            ) {
                // Left items
                NavigationBarItem(
                    selected = true,
                    onClick = { onBottomAction(0) },
                    icon = { Text("🏠") },
                    label = null
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onBottomAction(1) },
                    icon = { Text("🐾") },
                    label = null
                )

                // Reserve space inside the nav row for the center FAB (so icons don't sit exactamente debajo)
                // We place a Spacer with similar width to the fab to visually balance the bar.
                Spacer(modifier = Modifier.width(64.dp))

                NavigationBarItem(
                    selected = false,
                    onClick = { onBottomAction(3) },
                    icon = { Text("🔔") },
                    label = null
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onBottomAction(4) },
                    icon = { Text("👤") },
                    label = null
                )
            }
        }
    ) { innerPadding ->
        // combine the scaffold insets with your own padding for the grid
        val gridContentPadding = PaddingValues(
            top = innerPadding.calculateTopPadding() + 8.dp,
            bottom = innerPadding.calculateBottomPadding() + 12.dp,
            start = 12.dp,
            end = 12.dp
        )

        val pets = state.pets
        val gridState = rememberLazyGridState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = gridState,
            modifier = Modifier
                .fillMaxSize(),
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
