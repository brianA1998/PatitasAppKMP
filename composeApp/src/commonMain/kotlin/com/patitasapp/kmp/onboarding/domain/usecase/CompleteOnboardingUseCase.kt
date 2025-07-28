package com.patitasapp.kmp.onboarding.domain.usecase

import com.patitasapp.kmp.onboarding.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(private val repository : OnboardingRepository) {

    /**
     * Marks the onboarding process as completed.
     */
    suspend operator fun invoke() {
        repository.completeOnboarding()
    }
}