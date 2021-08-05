package com.vaccinecertificate.rest

import com.vaccinecertificate.dto.CertificateDto
import com.vaccinecertificate.service.CertificateService
import org.springframework.web.bind.annotation.*

/**
 * Controller that handles REST requests regarding certificates.
 */
@RestController
class CertificateController(private val certificateService: CertificateService) {
    @GetMapping(path = ["/certificates/{id}"])
    fun getById(@PathVariable("id") id: Int): CertificateDto {
        return certificateService.getById(id)
    }

    @GetMapping(path = ["/certificates"])
    fun getCertificates(
            @RequestParam(required = false) nationalIdentityNumber: String?,
            @RequestParam(required = false) name: String?
    ): List<CertificateDto> {
        return when {
            nationalIdentityNumber != null -> {
                certificateService.getByNationalIdentityNumber(nationalIdentityNumber)
            }
            name != null -> {
                certificateService.getByFirstName(name)
            }
            else -> {
                certificateService.getAllCertificates()
            }
        }
    }

    @PostMapping(path = ["/certificates"])
    fun createCertificate(@RequestBody certificate: CertificateDto): CertificateDto {
        return certificateService.createCertificate(certificate)
    }
}
