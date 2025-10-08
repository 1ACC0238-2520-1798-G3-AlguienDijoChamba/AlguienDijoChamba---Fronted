package com.alguien.dijochamba.features.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val name: String,
    val professionalLevel: String,
    val completedJobs: Int,
    val availableBalance: Double,
    val pendingAmount: Double,
    val totalEarned: Double,
    val rating: Double,
    val requests: List<ServiceRequest>
)

data class ServiceRequest(
    val id: Int,
    val clientName: String,
    val serviceType: String,
    val location: String,
    val dateTime: String,
    val description: String,
    val price: Double
)

class ProfileViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            // Datos exactos como en la imagen
            _userProfile.value = UserProfile(
                name = "Carlos Rodriguez",
                professionalLevel = "Gold Professional",
                completedJobs = 127,
                availableBalance = 11200.0, // Actualizado a 11200
                pendingAmount = 450.0,
                totalEarned = 8750.0,
                rating = 4.9, // Nuevo campo para rating
                requests = listOf(
                    ServiceRequest(
                        id = 1,
                        clientName = "Maria Gonzalez",
                        serviceType = "Plumbing",
                        location = "San Isidro, Lima",
                        dateTime = "2024-10-15 at 14:00",
                        description = "Kitchen sink leak needs urgent repair",
                        price = 120.0
                    ),
                    ServiceRequest(
                        id = 2,
                        clientName = "Roberto Silva",
                        serviceType = "Electrical",
                        location = "Miraflores, Lima",
                        dateTime = "2024-10-16 at 09:00",
                        description = "Install new ceiling fan in living room",
                        price = 200.0
                    )
                )
            )
        }
    }

    fun updateSelectedTab(tabIndex: Int) {
        _selectedTab.value = tabIndex
    }

    fun acceptRequest(requestId: Int) {
        // Lógica para aceptar solicitud
        println("Request $requestId accepted")
    }

    fun declineRequest(requestId: Int) {
        // Lógica para rechazar solicitud
        println("Request $requestId declined")
    }
}