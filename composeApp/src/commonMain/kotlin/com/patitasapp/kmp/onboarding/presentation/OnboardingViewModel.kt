package com.patitasapp.kmp.onboarding.presentation


import com.patitasapp.kmp.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.patitasapp.kmp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class OnboardingViewModel(
    hasSeenUseCase: HasSeenOnboardingUseCase,
    private val completeUseCase: CompleteOnboardingUseCase
) {
    private val _hasSeen = hasSeenUseCase() // Flow<Boolean>
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