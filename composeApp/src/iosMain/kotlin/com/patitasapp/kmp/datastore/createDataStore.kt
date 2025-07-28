package com.patitasapp.kmp.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.patitasapp.kmp.core.utils.ONBOARDING_PREFERENCES_FILE
import com.patitasapp.kmp.core.utils.createDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSFileManager
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSUserDomainMask


@OptIn(ExperimentalForeignApi::class)
fun createDataStoreIos(): DataStore<Preferences> =
    createDataStore(
        producePath = {
            NSFileManager.defaultManager
                .URLForDirectory(
                    directory = NSDocumentDirectory,
                    inDomain = NSUserDomainMask,
                    appropriateForURL = null,
                    create = false,
                    error = null
                )!!
                .path + "/$ONBOARDING_PREFERENCES_FILE"
        }
    )