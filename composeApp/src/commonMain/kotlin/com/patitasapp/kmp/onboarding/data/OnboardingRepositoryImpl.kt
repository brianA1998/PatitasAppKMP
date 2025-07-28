package com.patitasapp.kmp.onboarding.data

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import com.patitasapp.kmp.onboarding.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class OnboardingRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : OnboardingRepository {

    private object Keys {
        val SEEN_ONBOARDING = booleanPreferencesKey("has_seen_onboarding")
    }

    override fun hasSeenOnboarding(): Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[Keys.SEEN_ONBOARDING] ?: false
            }
            .distinctUntilChanged()

    override suspend fun completeOnboarding() {
        dataStore.updateData { prefs ->
            prefs.toMutablePreferences().apply {
                this[Keys.SEEN_ONBOARDING] = true
            }
        }
    }
}