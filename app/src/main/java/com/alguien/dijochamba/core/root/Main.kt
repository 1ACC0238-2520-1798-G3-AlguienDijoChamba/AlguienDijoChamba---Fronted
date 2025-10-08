package pe.edu.upc.easyshop.core.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.alguien.dijochamba.core.ui.theme.EasyShopTheme
import com.alguien.dijochamba.features.profile.presentation.ProfileScreen
import com.alguien.dijochamba.features.profile.presentation.di.ProfileModule

@Composable
fun Main() {
    val selectedIndex = remember {
        mutableIntStateOf(0)
    }

    val navigationItems = listOf(
        NavigationItem(icon = Icons.Default.Home, label = "Home"),
        NavigationItem(icon = Icons.Default.List, label = "Requests"),
        NavigationItem(icon = Icons.Default.DateRange, label = "Calendar"),
        NavigationItem(icon = Icons.Default.AttachMoney, label = "Payments"),
        NavigationItem(icon = Icons.Default.Person, label = "Profile"),
    )

    Scaffold(
        bottomBar = {
            BottomAppBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex.intValue == index,
                        icon = {
                            Icon(item.icon, contentDescription = item.label)
                        },
                        label = {
                            Text(text = item.label)
                        },
                        onClick = {
                            selectedIndex.intValue = index
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // Siempre mostrar ProfileScreen, independientemente del tab seleccionado
            ProfileScreen()
        }
    }
}

data class NavigationItem(val icon: ImageVector, val label: String)

@Preview
@Composable
fun MainPreview() {
    EasyShopTheme {
        Main()
    }
}