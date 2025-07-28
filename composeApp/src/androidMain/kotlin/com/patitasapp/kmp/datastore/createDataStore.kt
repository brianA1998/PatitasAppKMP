package com.patitasapp.kmp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.patitasapp.kmp.core.utils.ONBOARDING_PREFERENCES_FILE
import com.patitasapp.kmp.core.utils.createDataStore
import java.io.File

/**
 * Creates a DataStore instance for storing onboarding preferences.
 *
 * @param context The Android context used to access the file system.
 * @return A DataStore instance for onboarding preferences.
 */
fun createDataStoreAndroid(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { File(context.filesDir, ONBOARDING_PREFERENCES_FILE).absolutePath }
    )