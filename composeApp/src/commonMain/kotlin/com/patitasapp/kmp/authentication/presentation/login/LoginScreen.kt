package com.patitasapp.kmp.authentication.presentation.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.patitasapp.kmp.authentication.presentation.login.components.LoginForm
import com.patitasapp.kmp.core.presentation.PatitasTitle
import org.jetbrains.compose.resources.painterResource
import patitasappkmp.composeapp.generated.resources.Res
import patitasappkmp.composeapp.generated.resources.loginbackground

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLoginSuccess()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Image(
            painter = painterResource(Res.drawable.loginbackground),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .graphicsLayer(
                    scaleX = 1.27f,
                    scaleY = 1.27f
                )
        )

        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background,
                        )
                    )
                )
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 200.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PatitasTitle(title = "Inicia sesión y únete a")
                PatitasTitle(title = "la comunidad")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 0.dp), // Sin padding inferior para que quede pegado al final
                contentAlignment = Alignment.BottomCenter
            ) {
                LoginForm(state, viewModel::onEvent)
            }
        }
    }
}