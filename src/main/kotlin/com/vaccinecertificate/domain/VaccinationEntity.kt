package com.vaccinecertificate.domain

import java.util.*
import javax.persistence.*

/**
 * Database representation of a certificate.
 */

@Entity(name = "vaccination")
data class VaccinationEntity(@Id @GeneratedValue val id: Int = 0,
                             val dose: Int,
                             val date: Date,
                             val type: String,
                             @ManyToOne
                             @JoinColumn(name="CERTIFICATE_ID")
                             val certificate: CertificateEntity?
                            )

