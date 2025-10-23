package com.patitasapp.kmp.pets.presentation.create

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CreatePetViewModel {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _ui = MutableStateFlow(CreatePetState())
    val ui: StateFlow<CreatePetState> = _ui.asStateFlow()

    // --- setters
    fun onPhotoSelected(url: String?)        { _ui.value = _ui.value.copy(photoUrl = url) }
    fun onNameChange(v: String)              { _ui.value = _ui.value.copy(name = v) }
    fun onSpeciesChange(v: String)           { _ui.value = _ui.value.copy(species = v) }
    fun onBreedChange(v: String)             { _ui.value = _ui.value.copy(breed = v) }
    fun onGenderChange(g: PetGender?)        { _ui.value = _ui.value.copy(gender = g) }
    fun onBirthdayChange(v: String)          { _ui.value = _ui.value.copy(birthday = v) }
    fun onNeuteredToggle(v: Boolean)         { _ui.value = _ui.value.copy(neutered = v) }

    fun save(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val s = _ui.value
        if (!s.canSave) return

        scope.launch {
            try {
                _ui.value = s.copy(isSaving = true, error = null)
                kotlinx.coroutines.delay(500)
                _ui.value = _ui.value.copy(isSaving = false)
                onSuccess()
            } catch (t: Throwable) {
                _ui.value = _ui.value.copy(isSaving = false, error = t.message ?: "Error guardando")
                onError(_ui.value.error ?: "Error")
            }
        }
    }
}