package com.patitasapp.kmp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.patitasapp.kmp.core.utils.ONBOARDING_PREFERENCES_FILE
import com.patitasapp.kmp.core.utils.createDataStore



fun createDataStoreAndroid(context: Context): DataStore<Preferences> =
    createDataStore { context.filesDir.resolve(ONBOARDING_PREFERENCES_FILE).absolutePath }