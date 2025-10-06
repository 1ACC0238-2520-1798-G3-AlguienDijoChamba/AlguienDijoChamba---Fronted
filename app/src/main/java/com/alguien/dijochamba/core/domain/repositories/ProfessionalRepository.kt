package com.alguien.dijochamba.core.domain.repositories

import com.alguien.dijochamba.core.domain.models.Professional

interface ProfessionalRepository {
    suspend fun registerProfessional(professional: Professional): Result<Boolean>
    suspend fun validateProfessionalData(professional: Professional): Result<Boolean>
}