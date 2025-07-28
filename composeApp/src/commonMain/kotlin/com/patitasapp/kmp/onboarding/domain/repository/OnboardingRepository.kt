package com.patitasapp.kmp.onboarding.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    /**
     * Checks if the user has completed the onboarding process.
     *
     * @return true if the onboarding has been completed, false otherwise.
     */
    fun hasSeenOnboarding(): Flow<Boolean>

    /**
     * Marks the onboarding process as completed.
     */
    suspend fun completeOnboarding()
}