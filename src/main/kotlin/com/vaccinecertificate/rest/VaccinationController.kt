package com.vaccinecertificate.rest

import com.vaccinecertificate.dto.CertificateDto
import com.vaccinecertificate.dto.VaccinationDto
import com.vaccinecertificate.service.CertificateService
import com.vaccinecertificate.service.VaccinationService
import org.springframework.web.bind.annotation.*

/**
 * Controller that handles REST requests regarding vaccinations.
 */
@RestController
class VaccinationController(private val vaccinationService: VaccinationService) {
    @PostMapping(path = ["/vaccinations"])
    fun createVaccination(@RequestBody vaccination: VaccinationDto): VaccinationDto {
        return vaccinationService.createVaccination(vaccination)
    }
}
