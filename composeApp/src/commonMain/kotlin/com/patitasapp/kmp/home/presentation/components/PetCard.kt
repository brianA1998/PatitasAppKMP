package com.patitasapp.kmp.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.request.ImageRequest
import com.patitasapp.kmp.core.utils.Logger
import com.patitasapp.kmp.home.domain.Gender
import com.patitasapp.kmp.home.domain.Pet

@Composable
fun PetCard(pet: Pet, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {


            Logger.genericMessage("Antes de AsyncImage" + pet.imagenUrl)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pet.imagenUrl)
                    .crossfade(true)
                    .listener(
                        onError = { _, result ->
                            result.throwable.printStackTrace()
                            Logger.genericMessage("❌ Coil error: ${result.throwable.message}")
                        },
                        onSuccess = { _, _ ->
                            Logger.genericMessage("✅ Coil success para ${pet.name}")
                        }
                    )
                    .build(),
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            pet.imagenUrl?.let { Logger.genericMessage(it) }
            // 🐾 Info básica
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = pet.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Ubicación",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                // ♂ / ♀ símbolo
                Text(
                    text = when (pet.gender) {
                        Gender.MALE -> "♂"
                        Gender.FEMALE -> "♀"
                        Gender.OTHER -> ""
                    },
                    fontSize = 18.sp,
                    color = if (pet.gender == Gender.MALE) Color.Blue else Color.Magenta
                )
            }
        }
    }
}