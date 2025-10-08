package com.alguien.dijochamba.features.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alguien.dijochamba.core.ui.theme.EasyShopTheme
import com.alguien.dijochamba.features.profile.presentation.di.ProfileModule

@Composable
fun ProfileScreen(
    onBack: () -> Unit = {},
    viewModel: ProfileViewModel = ProfileModule.getProfileViewModel()
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()

    Scaffold(
        bottomBar = {
            ProfileBottomNavigation(
                selectedTab = selectedTab,
                onTabSelected = viewModel::updateSelectedTab
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            item {
                // Header con información del usuario
                ProfileHeader(userProfile)
            }

            item {
                // Balance y estadísticas
                BalanceSection(userProfile)
            }

            item {
                // Tabs de navegación
                ProfileTabs(selectedTab, viewModel::updateSelectedTab)
            }

            // Contenido basado en el tab seleccionado
            when (selectedTab) {
                0 -> { // Requests
                    item {
                        RequestsContent(userProfile, viewModel)
                    }
                }
                1 -> { // Balance
                    item {
                        BalanceContent(userProfile)
                    }
                }
                2 -> { // Earnings
                    item {
                        EarningsContent(userProfile)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(userProfile: UserProfile?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2563EB))
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Welcome back,",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )

        Text(
            text = userProfile?.name ?: "Carlos Rodriguez",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = userProfile?.professionalLevel ?: "Gold Professional",
                color = Color(0xFFFFD700), // Dorado
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            // Rating
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF10B981)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "4.9",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Completed Jobs ${userProfile?.completedJobs ?: 127}",
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
fun BalanceSection(userProfile: UserProfile?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Available Balance",
                color = Color(0xFF64748B),
                fontSize = 14.sp
            )

            Text(
                text = "S/${userProfile?.availableBalance ?: 11200}",
                color = Color(0xFF1E293B),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProfileTabs(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Requests", "Balance", "Earnings")

    TabRow(
        selectedTabIndex = selectedTab,
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.White,
        contentColor = Color(0xFF2563EB),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                height = 3.dp,
                color = Color(0xFF2563EB)
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                    )
                },
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                selectedContentColor = Color(0xFF2563EB),
                unselectedContentColor = Color(0xFF64748B)
            )
        }
    }
}

@Composable
fun RequestsContent(userProfile: UserProfile?, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // New Requests Section
        Text(
            text = "New Requests",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // María González Request
        NewRequestCard(
            request = ServiceRequest(
                id = 1,
                clientName = "Maria Gonzalez",
                serviceType = "Plumbing",
                location = "San Isidro, Lima",
                dateTime = "2024-10-15 at 14:00",
                description = "Kitchen sink leak needs urgent repair",
                price = 120.0
            ),
            onAccept = { viewModel.acceptRequest(1) },
            onDecline = { viewModel.declineRequest(1) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Roberto Silva Request
        NewRequestCard(
            request = ServiceRequest(
                id = 2,
                clientName = "Roberto Silva",
                serviceType = "Electrical",
                location = "Miraflores, Lima",
                dateTime = "2024-10-16 at 09:00",
                description = "Install new ceiling fan in living room",
                price = 200.0
            ),
            onAccept = { viewModel.acceptRequest(2) },
            onDecline = { viewModel.declineRequest(2) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Recent Completed Section
        Text(
            text = "Recent Completed",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Ana Torres Completed Job
        CompletedJobCard(
            clientName = "Ana Torres",
            serviceType = "Carpentry",
            location = "Surco, Lima",
            dateTime = "2024-10-14 at 18:00",
            description = "Custom bookshelf installation",
            price = 350.0
        )
    }
}

@Composable
fun NewRequestCard(
    request: ServiceRequest,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header con nombre del cliente y precio
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = request.clientName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )

                Text(
                    text = "S/${request.price.toInt()}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2563EB)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tipo de servicio
            Text(
                text = request.serviceType,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF475569)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Ubicación
            Text(
                text = request.location,
                fontSize = 14.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Fecha y hora
            Text(
                text = request.dateTime,
                fontSize = 14.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Descripción
            Text(
                text = request.description,
                fontSize = 14.sp,
                color = Color(0xFF374151),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onDecline,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEF4444),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(0.48f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Decline", fontSize = 16.sp)
                }

                Button(
                    onClick = onAccept,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF10B981),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(0.48f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Accept", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun CompletedJobCard(
    clientName: String,
    serviceType: String,
    location: String,
    dateTime: String,
    description: String,
    price: Double
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header con nombre del cliente y precio
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = clientName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )

                Text(
                    text = "S/${price.toInt()}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF10B981)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tipo de servicio
            Text(
                text = serviceType,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF475569)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Ubicación
            Text(
                text = location,
                fontSize = 14.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Fecha y hora
            Text(
                text = dateTime,
                fontSize = 14.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color(0xFF374151),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Status completado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Completed",
                    tint = Color(0xFF10B981),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Completed",
                    fontSize = 14.sp,
                    color = Color(0xFF10B981),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun BalanceContent(userProfile: UserProfile?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Card de detalles de pago
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Header con información del servicio
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "María González",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )

                    Text(
                        text = "S/120",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2563EB)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Plumbing",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF475569)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "San Isidro, Lima",
                    fontSize = 14.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "2024-10-15 at 14:00",
                    fontSize = 14.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Kitchen sink leak needs urgent repair",
                    fontSize = 14.sp,
                    color = Color(0xFF374151),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFE5E7EB))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Detalles de pago
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Total Amount
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Amount:",
                            fontSize = 16.sp,
                            color = Color(0xFF374151)
                        )
                        Text(
                            text = "S/120",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                    }

                    // Initial Payment
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Initial Payment:",
                            fontSize = 16.sp,
                            color = Color(0xFF374151)
                        )
                        Text(
                            text = "S/60",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                    }

                    // Final Payment
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Final Payment:",
                            fontSize = 16.sp,
                            color = Color(0xFF374151)
                        )
                        Text(
                            text = "S/60",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botón Payments
                Button(
                    onClick = { /* Navigate to payments */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563EB),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Payments", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun EarningsContent(userProfile: UserProfile?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Card de Earnings
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Pending
                EarningsItem(
                    title = "Pending",
                    amount = "S/450",
                    color = Color(0xFFF59E0B) // Amber
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Available
                EarningsItem(
                    title = "Available",
                    amount = "S/1250",
                    color = Color(0xFF10B981) // Emerald
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Total Earned
                EarningsItem(
                    title = "Total Earned",
                    amount = "S/8750",
                    color = Color(0xFF2563EB) // Blue
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón View Payment Details
                Button(
                    onClick = { /* Navigate to payment details */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563EB),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "View Payment Details",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun EarningsItem(
    title: String,
    amount: String,
    color: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF374151),
            fontWeight = FontWeight.Medium
        )

        Text(
            text = amount,
            fontSize = 18.sp,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProfileBottomNavigation(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val navigationItems = listOf(
        BottomNavigationItem("Home", Icons.Default.Home),
        BottomNavigationItem("Requests", Icons.Default.List),
        BottomNavigationItem("Calendar", Icons.Default.DateRange),
        BottomNavigationItem("Payments", Icons.Default.AttachMoney),
        BottomNavigationItem("Profile", Icons.Default.Person)
    )

    NavigationBar {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 12.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF2563EB),
                    selectedTextColor = Color(0xFF2563EB),
                    unselectedIconColor = Color(0xFF64748B),
                    unselectedTextColor = Color(0xFF64748B),
                    indicatorColor = Color.White
                )
            )
        }
    }
}

data class BottomNavigationItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    EasyShopTheme {
        ProfileScreen()
    }
}