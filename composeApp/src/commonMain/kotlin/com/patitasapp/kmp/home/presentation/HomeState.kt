package com.patitasapp.kmp.home.presentation

data class HomeState(
    val isLoading: Boolean = false,
    val pets: List<Any> = emptyList() // reemplaza Any por tu modelo real cuando lo tengas
) {
    val isEmpty: Boolean get() = !isLoading && pets.isEmpty()
}