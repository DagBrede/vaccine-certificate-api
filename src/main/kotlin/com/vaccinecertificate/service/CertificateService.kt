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

        return CertificateDtoMapper.from(certificateEntity)
    }

    /**
     * Gets the certificate with the given name.
     *
     * @param firstName name of the certificate to be retrieved
     * @return the certificate with the given name
     * @throws CertificateNotFoundException if no certificate with given name exists
     */
    fun getByName(name: String): List<CertificateDto> {
        return certificateRepository.findAll().map{
            Pair(it,"${it.firstName.toUpperCase()} ${it.lastName.toUpperCase()}")
        }.filter{
            it.second.contains(name.toUpperCase());
        }.map{
            it.first!!
        }.map{
            CertificateDtoMapper.from(it)
        }
    }

    fun getByNationalIdentityNumber(nationalIdentityNumber: String): List<CertificateDto> {
        return certificateRepository.findByNationalIdentityNumber(nationalIdentityNumber)
                .map{CertificateDtoMapper.from(it)}
    }

    /**
     * Gets all the certificates.
     *
     * @return a list of certificates
     */
    fun getAllCertificates(): List<CertificateDto> {
        return certificateRepository.findAll()
            .map { CertificateDtoMapper.from(it) }
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

        return CertificateDtoMapper.from(createdCertificate)
    }
}
