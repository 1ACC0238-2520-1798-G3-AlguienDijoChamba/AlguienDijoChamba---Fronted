package com.alguien.dijochamba.core.domain.usecases

import com.alguien.dijochamba.core.domain.models.Professional

class ValidateProfessionalDataUseCase {
    operator fun invoke(professional: Professional): Boolean {
        return professional.dinNumber.isNotBlank() &&
                professional.email.isNotBlank() &&
                professional.phoneNumber.isNotBlank() &&
                professional.password.isNotBlank() &&
                professional.acceptedTerms
    }
}