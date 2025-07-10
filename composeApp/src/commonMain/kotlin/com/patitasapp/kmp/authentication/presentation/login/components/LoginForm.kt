package com.patitasapp.kmp.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.patitasapp.kmp.authentication.presentation.login.LoginEvent
import com.patitasapp.kmp.authentication.presentation.login.LoginState
import com.patitasapp.kmp.core.presentation.PatitasButton
import com.patitasapp.kmp.core.presentation.PatitasPasswordTextField
import com.patitasapp.kmp.core.presentation.PatitasTextField

@Composable
fun LoginForm(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.background(Color.White, shape = RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Login in with email",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.background
            )

            PatitasTextField(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.EmailChanged(it)) },
                contentDescription = "Enter email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                placeholder = "Email",
                errorMessage = state.errorMessage,
                isEnabled = !state.isLoading,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
            )

            PatitasPasswordTextField(
                value = state.password,
                onValueChange = { onEvent(LoginEvent.PasswordChanged(it)) },
                contentDescription = "Enter password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                placeholder = "Password",
                errorMessage = state.errorMessage,
                isEnabled = !state.isLoading,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.clearFocus()
                    onEvent(LoginEvent.Login)
                }),
            )

            PatitasButton(
                text = "Login",
                isEnabled = !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                onEvent(LoginEvent.Login)
            }

            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Forgot Password?",
                    color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline
                )
            }

            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = buildAnnotatedString {
                        append("Don't have an account? ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Sign up")
                        }
                    },
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }

        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}