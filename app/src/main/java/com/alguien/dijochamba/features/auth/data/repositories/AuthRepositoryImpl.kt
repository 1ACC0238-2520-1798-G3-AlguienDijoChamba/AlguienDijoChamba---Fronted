package com.alguien.dijochamba.features.auth.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.alguien.dijochamba.features.auth.data.remote.models.LoginRequestDto
import com.alguien.dijochamba.features.auth.data.remote.services.AuthService
import com.alguien.dijochamba.features.auth.domain.models.User
import com.alguien.dijochamba.features.auth.domain.repositories.AuthRepository

class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String
    ): User? = withContext(Dispatchers.IO) {
        val response = service.login(LoginRequestDto(username, password))

        if (response.isSuccessful) {
            response.body()?.let { loginResponseDto ->
                return@withContext User(
                    name = " ${loginResponseDto.firstName ?: ""} ${loginResponseDto.lastName ?: ""}",
                    image = loginResponseDto.image ?: "",
                    email = loginResponseDto.email ?: ""
                )
            }
        }

        return@withContext null
    }
}