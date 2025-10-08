package com.alguien.dijochamba.features.auth.domain.repositories

import com.alguien.dijochamba.features.auth.domain.models.User

interface AuthRepository {
    suspend fun login(username: String, password: String): User?
}