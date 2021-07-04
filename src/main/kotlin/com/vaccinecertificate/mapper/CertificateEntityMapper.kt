package com.vaccinecertificate.mapper

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.dto.CertificateDto

object CertificateEntityMapper {

    fun from(certificateDto: CertificateDto) = CertificateEntity(
        firstName = certificateDto.firstName,
        lastName = certificateDto.lastName,
        isVaccinated = certificateDto.isVaccinated
    )
}