package com.vaccinecertificate.mapper

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.domain.VaccinationEntity
import com.vaccinecertificate.dto.CertificateDto
import com.vaccinecertificate.dto.VaccinationDto
import java.util.*

object CertificateDtoMapper {
    fun from(certificateEntity: CertificateEntity, includeVaccinations: Boolean = true) = CertificateDto(
        certificateEntity.id,
        certificateEntity.firstName,
        certificateEntity.lastName,
        certificateEntity.nationalIdentityNumber,
        if(includeVaccinations) certificateEntity.vaccinations.map{ vaccination: VaccinationEntity -> VaccinationDtoMapper.from(vaccination, false) } else Collections.emptyList()
    )
}