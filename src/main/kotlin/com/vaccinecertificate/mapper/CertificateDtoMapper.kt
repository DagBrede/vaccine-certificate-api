package com.vaccinecertificate.mapper

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.dto.CertificateDto

object CertificateDtoMapper {
    fun from(certificateEntity: CertificateEntity) = CertificateDto(
        certificateEntity.id,
        certificateEntity.firstName,
        certificateEntity.lastName,
        certificateEntity.isVaccinated)
}