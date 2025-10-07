package com.alguien.dijochamba.presentation.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()

    // Estados para los campos del formulario
    val dniNumber = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val selectedPaymentMethod = remember { mutableStateOf(PaymentMethod.CREDIT_CARD) }
    val acceptedTerms = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título
        Text(
            text = "Create Professional Account",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // DNI Number
        Text(
            text = "DNI Number *",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        OutlinedTextField(
            value = dniNumber.value,
            onValueChange = { dniNumber.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("12345678")
            }
        )

        // Email
        Text(
            text = "Email *",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("your.email@example.com")
            }
        )

        // Phone Number
        Text(
            text = "Phone Number *",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("+51 999 999 999")
            }
        )

        // Password
        Text(
            text = "Password *",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text("Create a strong password")
            }
        )
        Text(
            text = "At least 6 characters with uppercase, lowercase, and number",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontSize = 12.sp
        )

        // Confirm Password
        Text(
            text = "Confirm Password *",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text("Confirm your password")
            }
        )

        // Preferred Payment Method
        Text(
            text = "Preferred Payment Method",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        // Opción Credit/Debit Card
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedPaymentMethod.value == PaymentMethod.CREDIT_CARD,
                onClick = { selectedPaymentMethod.value = PaymentMethod.CREDIT_CARD }
            )
            Text(
                text = "Credit/Debit Card",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Opción Digital Wallet
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedPaymentMethod.value == PaymentMethod.DIGITAL_WALLET,
                onClick = { selectedPaymentMethod.value = PaymentMethod.DIGITAL_WALLET }
            )
            Text(
                text = "Digital Wallet",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Terms and Conditions
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = acceptedTerms.value,
                onCheckedChange = { acceptedTerms.value = it }
            )
            Text(
                text = "I accept the Terms & Conditions and Privacy Policy",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Create Account Button - ACTUALIZADO para navegar a completeProfile
        Button(
            onClick = {
                // Navegar a la pantalla de completar perfil
                navController.navigate("completeProfile")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            ),
            enabled = acceptedTerms.value // Solo habilitado si acepta términos
        ) {
            Text(
                text = "Create Account",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

enum class PaymentMethod {
    CREDIT_CARD, DIGITAL_WALLET
}