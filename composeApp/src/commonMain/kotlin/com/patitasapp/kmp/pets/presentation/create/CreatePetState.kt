package com.patitasapp.kmp.pets.presentation.create

enum class PetGender { MALE, FEMALE }

data class CreatePetState(
    val photoUrl: String? = null,
    val name: String = "",
    val species: String = "",
    val breed: String = "",
    val gender: PetGender? = null,
    val birthday: String = "",
    val neutered: Boolean = false,
    val isSaving: Boolean = false,
    val error: String? = null
) {
    val canSave: Boolean
        get() = name.isNotBlank() && species.isNotBlank() && !isSaving
}
