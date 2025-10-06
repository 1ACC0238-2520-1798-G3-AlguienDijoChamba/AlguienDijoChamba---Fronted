package com.alguien.dijochamba.core.domain.usecases

import com.alguien.dijochamba.core.domain.models.Professional
import com.alguien.dijochamba.core.domain.repositories.ProfessionalRepository

class RegisterProfessionalUseCase(
    private val repository: ProfessionalRepository
) {
    suspend operator fun invoke(professional: Professional): Result<Boolean> {
        return repository.registerProfessional(professional)
    }
}