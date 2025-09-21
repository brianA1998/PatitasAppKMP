package com.patitasapp.kmp.home.domain

enum class Gender { MALE, FEMALE, OTHER }


data class Pet(
    val id: String,
    val name: String,
    val gender: Gender,
    val imagenUrl: String? = null
)
