package com.vaccinecertificate.dto

import java.util.*

/**
 * Data transfer object representing a vaccination.
 */
data class VaccinationDto(
        val id: Int?,
        val dose: Int,
        val date: Date,
        val type: String,
        val vaccinatorIdentificator: String,
        val certificate: CertificateDto?) {

    init {
        require(dose > 0) { "dose must be positive integer" }
    }
}
