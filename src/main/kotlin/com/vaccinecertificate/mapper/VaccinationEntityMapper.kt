package com.vaccinecertificate.mapper

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.domain.VaccinationEntity
import com.vaccinecertificate.dto.CertificateDto
import com.vaccinecertificate.dto.VaccinationDto

object VaccinationEntityMapper {

    fun from(vaccinationDto: VaccinationDto, certificateEntity: CertificateEntity? = null): VaccinationEntity{
        return VaccinationEntity(
                dose = vaccinationDto.dose,
                date = vaccinationDto.date,
                type = vaccinationDto.type,
                vaccinatorIdentificator = vaccinationDto.vaccinatorIdentificator,
                certificate = certificateEntity
                        ?: if (vaccinationDto.certificate != null) {
                            CertificateEntityMapper.from(vaccinationDto.certificate)
                        } else {
                            null
                        }
        )
    }
}