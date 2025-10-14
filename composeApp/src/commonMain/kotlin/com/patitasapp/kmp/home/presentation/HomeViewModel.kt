package com.patitasapp.kmp.home.presentation

import com.patitasapp.kmp.home.domain.Gender
import com.patitasapp.kmp.home.domain.Pet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _uiState = MutableStateFlow(HomeState(isLoading = true))
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init {
        // mock load
        scope.launch {
            _uiState.value = HomeState(isLoading = true)
            // Simular fetch
            val items = generateMockPets()
            _uiState.value = HomeState(isLoading = false, pets = items)
        }
    }

    private fun generateMockPets(): List<Pet> {
        return listOf(
            Pet("1", "Samantha", Gender.FEMALE, "https://share.google/images/zB4BpNhFaKEGob6Qc"),
            Pet("2", "Tigri", Gender.MALE, "https://placekitten.com/401/401"),
            Pet("3", "Luna", Gender.FEMALE, "https://picsum.photos/400/400"),
            Pet("4", "Max", Gender.MALE, "https://picsum.photos/401/401"),
            Pet("5", "Milo", Gender.MALE, "https://placekitten.com/402/402"),
            Pet("6", "Nala", Gender.FEMALE, "https://picsum.photos/402/402")
        )
    }

    // método público para refrescar si querés
    fun refresh() {
        scope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val items = generateMockPets()
            _uiState.value = HomeState(isLoading = false, pets = items)
        }
    }

    // liberación manual si la necesitas
    fun clear() {
        // cancelar scope si querés; en KMP/Compose multiplatform gestión de ciclo la tenés que atar al lifecycle de la plataforma
    }
}