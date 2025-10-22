package com.patitasapp.kmp.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import patitasappkmp.composeapp.generated.resources.Res
import patitasappkmp.composeapp.generated.resources.dog_empty_state

@Composable
fun EmptyPetsScreen(
    onCreatePetClick: () -> Unit
) {
    // Paleta (Figma)
    val beige  = Color(0xFFFAF0E6) // fondo
    val violet = Color(0xFF6F5A85) // primario
    val text   = Color(0xFF534A57) // texto

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(beige)
            .systemBarsPadding()
    ) {
        // Base: menor lado como referencia (en Dp)
        val unit = min(maxWidth, maxHeight)

        // Helpers
        fun dpFactor(f: Float) = (unit.value * f).dp
        fun Dp.times(f: Float): Dp = (this.value * f).dp


        // Espaciados y tamaños con límites (todos en Dp/TextUnit de Compose)
        val topTitlePad    = dpFactor(0.055f).coerceAtLeast(16.dp).coerceAtMost(44.dp)
        val spaceTitleHalo = dpFactor(0.048f).coerceAtLeast(16.dp).coerceAtMost(36.dp)
        val spaceHaloText  = dpFactor(0.036f).coerceAtLeast(12.dp).coerceAtMost(28.dp)

        val haloSize   = dpFactor(0.40f).coerceAtLeast(200.dp).coerceAtMost(320.dp)
        val avatarSize = haloSize.times(0.52f)
        val paragraphMaxW = dpFactor(0.82f).coerceAtMost(500.dp)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(topTitlePad))

            Text(
                text = "NO SE HA ENCONTRADO\nMASCOTA",
                color = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.sp
                )
            )

            Spacer(Modifier.height(spaceTitleHalo))

            // Halo + avatar
            Box(
                modifier = Modifier.size(haloSize),
                contentAlignment = Alignment.Center
            ) {
                // halo radial suave
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Color(0x14000000), Color(0x00000000)), // 8% -> 0%
                                center = Offset.Unspecified,
                            )
                        )
                )
                Image(
                    painter = painterResource(Res.drawable.dog_empty_state),
                    contentDescription = null,
                    modifier = Modifier
                        .size(avatarSize)
                        .clip(CircleShape)
                )
            }

            Spacer(Modifier.height(spaceHaloText))

            Box(Modifier.widthIn(max = paragraphMaxW)) {
                Text(
                    text = "Añade tu primer mascota  haciendo click en el\nbotón “+” superior o en el botón de abajo",
                    color = text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 22.sp
                    )
                )
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = onCreatePetClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = violet,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Crear mascota",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}
