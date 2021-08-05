package com.vaccinecertificate.repository

import com.vaccinecertificate.domain.VaccinationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

/**
 * Repository for vaccinations..
 */
@Transactional
interface VaccinationRepository : JpaRepository<VaccinationEntity, Int> { }
