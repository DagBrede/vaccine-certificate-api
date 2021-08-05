package com.vaccinecertificate.service

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.dto.CertificateDto
import com.vaccinecertificate.dto.VaccinationDto
import com.vaccinecertificate.exception.CertificateNotFoundException
import com.vaccinecertificate.mapper.CertificateDtoMapper
import com.vaccinecertificate.mapper.CertificateEntityMapper
import com.vaccinecertificate.mapper.VaccinationDtoMapper
import com.vaccinecertificate.mapper.VaccinationEntityMapper
import com.vaccinecertificate.repository.CertificateRepository
import com.vaccinecertificate.repository.VaccinationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

/**
 * Service for functionality regarding vaccinations.
 */
@Service
class VaccinationService(
        private val vaccinationRepository: VaccinationRepository,
        private val certificateRepository: CertificateRepository
        ) {
    fun createVaccination(vaccinationDto: VaccinationDto): VaccinationDto {
        val certificateFromVaccinationDto = vaccinationDto.certificate!!

        val savedCertificate: CertificateEntity = if(certificateFromVaccinationDto.id != null){
            certificateRepository.findById(certificateFromVaccinationDto.id).get()
        } else {
            certificateRepository.save(CertificateEntity(
                    firstName = certificateFromVaccinationDto.firstName,
                    lastName = certificateFromVaccinationDto.lastName,
                    nationalIdentityNumber = certificateFromVaccinationDto.nationalIdentityNumber,
                    vaccinations =  Collections.emptyList()
            ))
        }


        val vaccinationEntity = VaccinationEntityMapper.from(vaccinationDto, savedCertificate)

        val createdVaccination = vaccinationRepository.save(vaccinationEntity)

        return VaccinationDtoMapper.from(createdVaccination)
    }
}
