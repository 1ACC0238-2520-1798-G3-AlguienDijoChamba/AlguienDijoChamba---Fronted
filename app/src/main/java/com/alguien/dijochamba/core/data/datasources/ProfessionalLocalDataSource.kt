package com.alguien.dijochamba.core.data.datasources

import com.alguien.dijochamba.core.domain.models.Professional

class ProfessionalLocalDataSource {
    private val professionals = mutableListOf<Professional>()

    fun saveProfessional(professional: Professional) {
        professionals.add(professional)
    }

    fun getProfessionals(): List<Professional> {
        return professionals.toList()
    }
}