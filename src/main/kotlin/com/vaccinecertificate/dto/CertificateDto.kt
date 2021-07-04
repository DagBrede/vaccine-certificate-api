package com.vaccinecertificate.dto

/**
 * Data transfer object representing a certificate.
 */
data class CertificateDto(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val isVaccinated: Boolean) {

    init {
        require(firstName.length >= 2) { "Name must have length >= 2!" }
    }
}
