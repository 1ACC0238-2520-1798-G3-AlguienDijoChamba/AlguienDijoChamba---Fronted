package com.alguien.dijochamba.core.domain.models

data class Professional(
    val dinNumber: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val preferredPaymentMethod: PaymentMethod,
    val acceptedTerms: Boolean
)

enum class PaymentMethod {
    CREDIT_CARD, DIGITAL_WALLET
}

data class User(
    val email: String,
    val password: String
)