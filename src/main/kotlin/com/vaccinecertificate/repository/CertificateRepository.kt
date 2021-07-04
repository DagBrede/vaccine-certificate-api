package com.vaccinecertificate.repository

import com.vaccinecertificate.domain.CertificateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

/**
 * Repository for certificates..
 */
@Transactional
interface CertificateRepository : JpaRepository<CertificateEntity, Int> {

    /**
     * Finds a certificate by first name.
     *
     * @param firstName the firstName of the certificate we want to find
     * @return list of certificates found
     */
    fun findByFirstName(firstName: String): List<CertificateEntity>
}
