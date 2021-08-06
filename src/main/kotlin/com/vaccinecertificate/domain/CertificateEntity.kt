package com.vaccinecertificate.domain

import javax.persistence.*

/**
 * Database representation of a certificate.
 */
@Entity(name = "certificate")
data class CertificateEntity(@Id @GeneratedValue val id: Int = 0,
                             val firstName: String,
                             val lastName: String,
                             val nationalIdentityNumber: String,
                             @OneToMany(mappedBy = "certificate")
                             val vaccinations: List<VaccinationEntity>

){
    override fun toString(): String {
        return "firstName: $firstName, lastName: $lastName, nationalIdentityNumber: $nationalIdentityNumber, vaccinations:  ${vaccinations.map{it.toString()}} "
    }
}

