package com.alguien.dijochamba.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderWithBack(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(105.dp)
                .padding(horizontal = 16.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.size(40.dp)
            ) {
                Text(
                    text = "<",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { onBack() }
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    color = Color(0xFF1F2937)
                )
            )

            Spacer(modifier = Modifier.width(40.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFF6B7280))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderWithBackPreview() {
    HeaderWithBack(title = "Create Professional Account", onBack = {})
}
