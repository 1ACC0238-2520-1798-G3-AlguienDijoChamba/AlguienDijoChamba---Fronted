package com.alguien.dijochamba.features.auth.presentation.register

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.alguien.dijochamba.core.ui.theme.EasyShopTheme
import com.alguien.dijochamba.features.presentation.components.HeaderWithBack

@Composable
fun RegisterForm(
    onRegisterProfile: (String) -> Unit
) {
    var dni by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var acceptTerms by remember { mutableStateOf(false) }
    var selectedPayment by remember { mutableStateOf("Credit/Debit Card") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        HeaderWithBack(
            title = "Create Professional Account",
            onBack = { /* volver */ },
            modifier = Modifier.fillMaxWidth()
        )

        // Padding solo para el formulario
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // DNI
            OutlinedTextField(
                value = dni,
                onValueChange = { dni = it },
                label = { Text("DNI Number *") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            // nombre
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Name *") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email *") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Phone
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number *") },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password *") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Confirm Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password *") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(25.dp))

            // Preferred Payment Method
            Text("Preferred Payment Method", color = Color(0xFF1F2937))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { selectedPayment = "Credit/Debit Card" },
                    modifier = Modifier
                        .width(180.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedPayment == "Credit/Debit Card") Color(0xFF2563EB) else Color(0xFFEFF6FF) ,
                        contentColor   = if (selectedPayment == "Credit/Debit Card") Color.White else Color.Black

                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("💳 Credit/Debit Card")
                }

                Button(
                    onClick = { selectedPayment = "Digital Wallet" },
                    modifier = Modifier
                        .width(180.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedPayment == "Credit/Debit Card") Color(0xFFEFF6FF) else Color(0xFF2563EB) ,
                        contentColor   = if (selectedPayment == "Credit/Debit Card") Color.Black else Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("📱 Digital Wallet")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.Top) {
                Checkbox(checked = acceptTerms, onCheckedChange = { acceptTerms = it })
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    buildAnnotatedString {
                        append("I accept the ")
                        withStyle(style = SpanStyle(color = Color(0xFF2563EB))) {
                            append("Terms & Conditions")
                        }
                        append(" and ")
                        withStyle(style = SpanStyle(color = Color(0xFF2563EB))) {
                            append("Privacy Policy")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(44.dp))

            // Register Button
            Button(
                onClick = { onRegisterProfile(userName)  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2563EB),
                    contentColor = Color.White
                )
            ) {
                Text("Create Account")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterFormPreview() {
    EasyShopTheme {
        RegisterForm(
            onRegisterProfile = { userName ->}
        )
    }
}
