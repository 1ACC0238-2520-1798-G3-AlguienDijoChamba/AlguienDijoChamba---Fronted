package com.alguien.dijochamba.core.data.repositories

import com.alguien.dijochamba.core.domain.models.Professional
import com.alguien.dijochamba.core.domain.repositories.ProfessionalRepository
import com.alguien.dijochamba.core.data.datasources.ProfessionalLocalDataSource

class ProfessionalRepositoryImpl(
    private val localDataSource: ProfessionalLocalDataSource
) : ProfessionalRepository {

    override suspend fun registerProfessional(professional: Professional): Result<Boolean> {
        return try {
            localDataSource.saveProfessional(professional)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun validateProfessionalData(professional: Professional): Result<Boolean> {
        return try {
            // Validaciones básicas
            val isValid = professional.dinNumber.length == 8 &&
                    professional.email.contains("@") &&
                    professional.phoneNumber.length >= 10 &&
                    professional.password.length >= 6 &&
                    professional.acceptedTerms

            Result.success(isValid)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}