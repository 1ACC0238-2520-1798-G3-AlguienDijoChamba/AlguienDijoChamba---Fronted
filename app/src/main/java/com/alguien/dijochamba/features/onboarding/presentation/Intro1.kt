package com.alguien.dijochamba.features.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.alguien.dijochamba.R

@Composable
fun Intro1(
    onContinue: () -> Unit
) {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 100.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Contenedor circular con la imagen
            Box(
                modifier = Modifier
                    .size(192.dp)
                    .clip(CircleShape) // <- Esto asegura que todo dentro sea circular
                    .background(Color(0xFFF9FAFB))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onboarding_image),
                    contentDescription = "Professional services illustration",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // <- Recorta la imagen para que encaje en el círculo
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Grow your work opportunities",
                    color = Color(0xFF1F2937),
                    fontSize = 24.sp,
                    lineHeight = 32.sp
                )
                Text(
                    text = "with AlguienDijoChamba",
                    color = Color(0xFF2563EB),
                    fontSize = 18.sp,
                    lineHeight = 28.sp
                )
                Text(
                    text = "Connect with clients who need your professional services. Join thousands of verified technicians growing their business.",
                    color = Color(0xFF6B7280),
                    fontSize = 16.sp,
                    lineHeight = 26.sp,
                    modifier = Modifier.width(320.dp),
                )
            }
        }

        // Botón Continue
        Button(
            onClick = { onContinue() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .width(166.dp)
                .height(80.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            Text(text = "Continue", color = Color.White, fontSize = 18.sp)
        }

        // Indicador de progreso
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 64.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color(0xFF2563EB), shape = CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color(0xFFE5E7EB), shape = CircleShape)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Intro1Preview() {
    Intro1(
        onContinue = { /* No action for preview */ }
    )
}