package com.alguien.dijochamba.features.onboarding.presentation

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun Intro2(
    onContinue: () -> Unit,
    onCreateAccount: () -> Unit
) {
    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Contenedor central
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 100.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Contenedor circular con la imagen para Intro2
            Box(
                modifier = Modifier
                    .size(192.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF9FAFB))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onboarding_image2),
                    contentDescription = "Professional community illustration",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Ready to start",
                    color = Color(0xFF1F2937),
                    fontSize = 24.sp,
                    lineHeight = 32.sp
                )
                Text(
                    text = "Join our professional commuity",
                    color = Color(0xFF2563EB),
                    fontSize = 18.sp,
                    lineHeight = 28.sp
                )
                Text(
                    text = "Create your profeessional profile and start receiving request from verified clients in your area. ",
                    color = Color(0xFF6B7280),
                    fontSize = 16.sp,
                    lineHeight = 26.sp,
                    modifier = Modifier.width(320.dp),
                )
            }
        }

        // Boton Continue
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onContinue()} ,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(166.dp)
                    .height(50.dp)
            ) {
                Text(text = "Continue", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "I already had account",
                color = Color(0xFF6B7280),
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    onCreateAccount()
                }
            )
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
                    .background(Color(0xFFE5E7EB), shape = CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color(0xFF2563EB), shape = CircleShape)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Intro2Preview() {
    Intro2(
        onContinue = {  },
        onCreateAccount = {  }
    )

}