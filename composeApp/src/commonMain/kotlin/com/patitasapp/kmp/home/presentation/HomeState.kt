package com.patitasapp.kmp.home.presentation

import com.patitasapp.kmp.home.domain.Pet

data class HomeState(
    val isLoading: Boolean = false,
    val pets: List<Pet> = emptyList(),
    val error: String? = null
)
