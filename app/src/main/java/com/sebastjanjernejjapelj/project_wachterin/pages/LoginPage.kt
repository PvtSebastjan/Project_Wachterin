package com.sebastjanjernejjapelj.project_wachterin.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sebastjanjernejjapelj.project_wachterin.R
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.AppTypography
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.secondaryLightMediumContrast


@Composable
fun LoginPage(onLoginClick: (String, String) -> Unit, onExitClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 60.dp),
        contentPadding = PaddingValues(bottom = 20.dp), // Adds padding at the bottom for the keyboard
        verticalArrangement = Arrangement.spacedBy(16.dp), // Adds space between elements
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier
                    .paint(
                        painterResource(id = R.drawable.projectwechtarin_logo),
                        contentScale = ContentScale.FillBounds
                    )
                    .padding(16.dp)
            )
            Text(
                text = "Welcome to Project Wachterin",
                fontSize = 30.sp,
                color = secondaryLightMediumContrast,
                style = AppTypography.displayLarge,
                modifier = Modifier.padding(top = 16.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            Text(
                text = "Please enter your username and password",
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            // Username field
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Username")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
            )
        }

        item {
            // Password field with toggle for visibility
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
            )
        }

        item {
            // Buttons row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Ensures spacing between the buttons
            ) {
                // Exit button
                OutlinedButton(
                    onClick = { onExitClick() },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text("Exit", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.width(16.dp)) // Add space between the buttons

                // Login button
                Button(
                    onClick = { onLoginClick(username, password) },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(text = "Login", fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    MaterialTheme {
        LoginPage(
            onLoginClick = { username, password ->
                println("Logging in with Username: $username and Password: $password")
            },
            onExitClick = {
                println("Exit clicked")
            }
        )
    }
}


