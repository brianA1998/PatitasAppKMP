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
            Pet(id = "1", name = "Samantha", gender = Gender.FEMALE),
            Pet(id = "2", name = "Tigri", gender = Gender.MALE),
            Pet(id = "3", name = "Luna", gender = Gender.FEMALE),
            Pet(id = "4", name = "Max", gender = Gender.MALE),
            Pet(id = "5", name = "Milo", gender = Gender.MALE),
            Pet(id = "6", name = "Nala", gender = Gender.FEMALE)
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