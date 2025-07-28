package com.patitasapp.kmp.onboarding.domain.usecase

import com.patitasapp.kmp.onboarding.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

class HasSeenOnboardingUseCase(private val repository: OnboardingRepository) {
    /**
     * Checks if the user has seen the onboarding process.
     *
     * @return true if the onboarding has been seen, false otherwise.
     */
    operator fun invoke(): Flow<Boolean> {
        return repository.hasSeenOnboarding()
    }
}