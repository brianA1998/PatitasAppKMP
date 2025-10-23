package com.patitasapp.kmp.pets.presentation.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.patitasapp.kmp.core.presentation.PatitasButton
import com.patitasapp.kmp.core.presentation.PatitasTextField
import com.patitasapp.kmp.core.presentation.PatitasTitle

@Composable
fun CreatePetScreen(
    viewModel: CreatePetViewModel = remember { CreatePetViewModel() },
    onPickPhoto: () -> Unit,      // abre el picker nativo (lo conectás después)
    onSaved: () -> Unit,          // vuelve a Home o a detalle
) {
    val state by viewModel.ui.collectAsState()

    // paleta Patitas
    val beige  = Color(0xFFFAF0E6)
    val violet = Color(0xFF6F5A85)
    val text   = Color(0xFF534A57)

    Scaffold(
        containerColor = beige,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(16.dp)
            ) {
                PatitasButton(
                    text = if (state.isSaving) "Guardando..." else "Guardar mascota",
                    isEnabled = state.canSave && !state.isSaving,
                    onClick = { viewModel.save(onSuccess = onSaved, onError = { /* show snackbar si querés */ }) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            PatitasTitle(
                title = "Nueva mascota",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp)
            )

            // --- Foto
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(128.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            listOf(Color(0x11_000000), Color(0x00000000))
                        )
                    )
                    .clickable { onPickPhoto() },
                contentAlignment = Alignment.Center
            ) {
                // Si todavía no tenés imagen, mostramos un círculo claro con “📷”
                val innerBg = Color(0xFFFFF3E9)
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(innerBg),
                    contentAlignment = Alignment.Center
                ) {
                    // Aquí podrías usar AsyncImage cuando conectes Storage
                    Text(text = if (state.photoUrl == null) "📷" else "🖼️", fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(Modifier.height(24.dp))

            // Nombre (obligatorio)
            PatitasTextField(
                value = state.name,
                onValueChange = viewModel::onNameChange,
                contentDescription = "Nombre de la mascota",
                placeholder = "Nombre (ej: Rocky)"
            )

            Spacer(Modifier.height(12.dp))

            // Especie (obligatorio) – dropdown simple
            PetDropdown(
                label = "Especie",
                options = listOf("Perro", "Gato", "Ave", "Reptil", "Roedor", "Otro"),
                selected = state.species,
                onSelected = viewModel::onSpeciesChange
            )

            Spacer(Modifier.height(12.dp))

            // Raza (opcional)
            PatitasTextField(
                value = state.breed,
                onValueChange = viewModel::onBreedChange,
                contentDescription = "Raza",
                placeholder = "Raza (opcional)"
            )

            Spacer(Modifier.height(12.dp))

            // Género – 2 botones segmentados
            GenderSegmented(
                selected = state.gender,
                onSelected = viewModel::onGenderChange,
                violet = violet,
                textColor = text
            )

            Spacer(Modifier.height(12.dp))

            // Cumpleaños – por ahora texto; luego lo cambiamos por date picker multiplatform
            PatitasTextField(
                value = state.birthday,
                onValueChange = viewModel::onBirthdayChange,
                contentDescription = "Cumpleaños",
                placeholder = "Cumpleaños (ej: 2019-07-15)"
            )

            Spacer(Modifier.height(8.dp))

            // Castrado – switch
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Castrado",
                    color = text,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = state.neutered,
                    onCheckedChange = viewModel::onNeuteredToggle,
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = violet)
                )
            }

            if (state.error != null) {
                Text(
                    text = state.error ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(80.dp)) // respiro sobre la bottom bar
        }
    }
}
