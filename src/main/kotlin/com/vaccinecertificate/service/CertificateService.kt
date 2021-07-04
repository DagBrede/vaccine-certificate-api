package com.vaccinecertificate.service

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.dto.CertificateDto
import com.vaccinecertificate.exception.CertificateNotFoundException
import com.vaccinecertificate.mapper.CertificateDtoMapper
import com.vaccinecertificate.mapper.CertificateEntityMapper
import com.vaccinecertificate.repository.CertificateRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * Service for functionality regarding certificates.
 */
@Service
class CertificateService(private val certificateRepository: CertificateRepository) {
    /**
     * Gets the certificate with the given id.
     *
     * @param id id of the certificate to be retrieved
     * @return the certificate with the given id
     * @throws CertificateNotFoundException if no certificate with given id exists
     */
    fun getById(id: Int): CertificateDto {
        val certificateEntity: CertificateEntity = certificateRepository.findByIdOrNull(id)
            ?: throw CertificateNotFoundException("Certificate with id $id not found")

        return certificateEntity.let(CertificateDtoMapper::from)
    }

    /**
     * Gets the certificate with the given name.
     *
     * @param name name of the certificate to be retrieved
     * @return the certificate with the given name
     * @throws CertificateNotFoundException if no certificate with given name exists
     */
    fun getByFirstName(firstName: String): List<CertificateDto> {
        return certificateRepository.findByFirstName(firstName)
            .map(CertificateDtoMapper::from)
            .toList()
    }

    /**
     * Gets all the certificates.
     *
     * @return a list of certificates
     */
    fun getAllCertificates(): List<CertificateDto> {
        return certificateRepository.findAll()
            .map(CertificateDtoMapper::from)
            .toList()
    }

    /**
     * Creates a certificate.
     *
     * @param certificateDto dto representing the certificate
     * @return dto representing the newly created certificate
     */
    fun createCertificate(certificateDto: CertificateDto): CertificateDto {
        val certificateEntity = certificateDto.let(CertificateEntityMapper::from)

        val createdCertificate = certificateRepository.save(certificateEntity)

        return createdCertificate.let(CertificateDtoMapper::from)
    }
}
