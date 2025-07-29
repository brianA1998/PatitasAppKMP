package com.patitasapp.kmp.onboarding.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patitasapp.kmp.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.patitasapp.kmp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OnboardingViewModel(
    hasSeenUseCase: HasSeenOnboardingUseCase,
    private val completeUseCase: CompleteOnboardingUseCase
) : ViewModel() {
    private val _hasSeen = hasSeenUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    val hasSeenOnboarding: Boolean
        get() = _hasSeen.value

    fun completeOnboarding() {
        viewModelScope.launch {
            completeUseCase()
        }
    }
}