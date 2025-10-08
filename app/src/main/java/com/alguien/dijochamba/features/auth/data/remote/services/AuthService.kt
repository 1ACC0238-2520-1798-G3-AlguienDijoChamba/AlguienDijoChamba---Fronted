package com.alguien.dijochamba.features.auth.data.remote.services

import com.alguien.dijochamba.features.auth.data.remote.models.LoginRequestDto
import com.alguien.dijochamba.features.auth.data.remote.models.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun login(@Body request: LoginRequestDto): Response<LoginResponseDto>

}