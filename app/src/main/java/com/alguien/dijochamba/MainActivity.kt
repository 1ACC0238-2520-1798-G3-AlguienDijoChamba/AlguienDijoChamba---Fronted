package com.alguien.dijochamba

import LoginForm
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alguien.dijochamba.core.navigation.Navigation
import com.alguien.dijochamba.core.ui.theme.EasyShopTheme
import com.alguien.dijochamba.features.onboarding.presentation.Intro1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyShopTheme {
                Navigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    EasyShopTheme {
        Intro1(
            onContinue = { println("Continue clicked")  }
        )
    }
}
