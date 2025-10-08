package com.alguien.dijochamba.features.auth.presentation.regiserProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.alguien.dijochamba.core.ui.theme.EasyShopTheme

@Composable
fun RegisterProfile(
    userName: String,
    onBack: () -> Unit = {},
    onSaveProfile: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    var profileImageUri by remember { mutableStateOf<String?>(null) }
    var selectedSpecialty by remember { mutableStateOf("") }
    var yearsExperience by remember { mutableStateOf("") }
    var hourlyRate by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var certificationFileName by remember { mutableStateOf("") }

    val specialties = listOf("Plumbing", "Electrical", "Cleaning", "Carpentry", "Painting")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "< Back",
                color = Color(0xFF2563EB),
                modifier = Modifier.clickable { onBack() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Create Professional Account",
                fontSize = 20.sp,
                color = Color(0xFF1F2937)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Profile Photo
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color(0xFFF9FAFB), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (profileImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(profileImageUri),
                    contentDescription = "Profile photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = "Upload Photo",
                    color = Color(0xFF6B7280),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Specialties
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Specialties",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth()
            ) {
                specialties.take(3).forEach { specialty ->
                    Button(
                        onClick = { selectedSpecialty = specialty },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedSpecialty == specialty) Color(0xFF2563EB) else Color(0xFFE5E7EB),
                            contentColor = if (selectedSpecialty == specialty) Color.White else Color.Black
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = specialty)
                    }
                }
            }

            if (specialties.size > 3) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    specialties.drop(3).forEach { specialty ->
                        Button(
                            onClick = { selectedSpecialty = specialty },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedSpecialty == specialty) Color(0xFF2563EB) else Color(0xFFE5E7EB),
                                contentColor = if (selectedSpecialty == specialty) Color.White else Color.Black
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = specialty)
                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))

        // Experience & Hourly Rate
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Years of Experience", fontSize = 14.sp, color = Color(0xFF1F2937))
                OutlinedTextField(
                    value = yearsExperience,
                    onValueChange = { yearsExperience = it },
                    placeholder = { Text("e.g., 5") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.AccessTime, contentDescription = "Years Icon")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text("Hourly Rate (S/)", fontSize = 14.sp, color = Color(0xFF1F2937))
                OutlinedTextField(
                    value = hourlyRate,
                    onValueChange = { hourlyRate = it },
                    placeholder = { Text("e.g., 50") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.AttachMoney, contentDescription = "Money Icon")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Professional Bio
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Professional Bio", fontSize = 14.sp, color = Color(0xFF1F2937))
            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                placeholder = { Text("Tell clients about your experience...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                maxLines = 5
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Certifications
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Certifications", fontSize = 14.sp, color = Color(0xFF1F2937))
            Button(
                onClick = { /* Subir archivo */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B7280)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(0.dp) // cuadrado
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (certificationFileName.isEmpty()) "Upload Certification" else certificationFileName,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }



        Spacer(modifier = Modifier.height(24.dp))

        // Profile Preview
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Profile Preview", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .border(2.dp, Color(0xFF6B7280), CircleShape)
                        .background(Color(0xFFF9FAFB), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (profileImageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(profileImageUri),
                            contentDescription = "Profile photo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Text(userName, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Save Button
        Button(
            onClick = { onSaveProfile() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Save Profile", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Skip
        Text(
            text = "Skip",
            color = Color(0xFF2563EB),
            modifier = Modifier.clickable { onSkip() }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterProfilePreview() {
    EasyShopTheme {
        RegisterProfile(userName = "Carlos Alberto Rodriguez")
    }
}