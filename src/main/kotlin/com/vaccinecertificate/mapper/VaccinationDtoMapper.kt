package com.vaccinecertificate.mapper

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.domain.VaccinationEntity
import com.vaccinecertificate.dto.CertificateDto
import com.vaccinecertificate.dto.VaccinationDto

object VaccinationDtoMapper {
    fun from(vaccinationEntity: VaccinationEntity, includeCertificate: Boolean = true): VaccinationDto {
        return VaccinationDto(
                id = vaccinationEntity.id,
                dose = vaccinationEntity.dose,
                date = vaccinationEntity.date,
                type= vaccinationEntity.type,
                certificate = if (includeCertificate && vaccinationEntity.certificate != null)
                    CertificateDtoMapper.from(vaccinationEntity.certificate, false)
                else
                    null
        )
    }
}