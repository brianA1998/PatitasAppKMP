package com.patitasapp.kmp.datastore

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.patitasapp.kmp.navigation.NavigationHost
import com.patitasapp.kmp.authentication.data.repository.AuthRepositoryImpl
import com.patitasapp.kmp.authentication.data.source.FirebaseAuthDataSource
import com.patitasapp.kmp.authentication.domain.usecase.SignInUseCase
import com.patitasapp.kmp.authentication.presentation.login.LoginViewModel
import com.patitasapp.kmp.onboarding.data.OnboardingRepositoryImpl

@Composable
fun AppAndroid() {
    val context = LocalContext.current
    NavigationHost(
        createOnboardingRepo = {
            val ds = createDataStoreAndroid(context)
            OnboardingRepositoryImpl(ds)
        },
        createAuthDeps = {
            val repo = AuthRepositoryImpl(FirebaseAuthDataSource())
            val use = SignInUseCase(repo)
            val vm = LoginViewModel(use)
            use to vm
        }
    )
}