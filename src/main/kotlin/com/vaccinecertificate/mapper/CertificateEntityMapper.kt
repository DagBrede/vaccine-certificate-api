package com.vaccinecertificate.mapper

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.dto.CertificateDto
import com.vaccinecertificate.dto.VaccinationDto

object CertificateEntityMapper {

    fun from(certificateDto: CertificateDto) = CertificateEntity(
        firstName = certificateDto.firstName,
        lastName = certificateDto.lastName,
        nationalIdentityNumber = certificateDto.nationalIdentityNumber,
        vaccinations = certificateDto.vaccinations.map{ vaccinationDto: VaccinationDto -> VaccinationEntityMapper.from(vaccinationDto) }
    )
}