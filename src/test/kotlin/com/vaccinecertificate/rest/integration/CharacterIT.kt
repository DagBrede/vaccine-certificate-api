package com.vaccinecertificate.rest.integration

import com.vaccinecertificate.Application
import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.repository.CertificateRepository
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsString
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@AutoConfigureMockMvc
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Application::class])
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CertificateIT {
    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var certificateRepository: CertificateRepository

    @Test
    fun getCertificateByIdReturnsExistingCertificate() {
        val certificateEntity = CertificateEntity(1, "Bruce", "Wayne", true)

        certificateRepository.save(certificateEntity)

        mvc.perform(MockMvcRequestBuilders.get("/certificates/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id", `is`(certificateEntity.id)))
    }

    @Test
    fun getCertificateByIdReturnsNotFoundOnNonExistingCertificate() {
        mvc.perform(MockMvcRequestBuilders.get("/certificates/42").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
            .andExpect(status().reason(containsString("Could not find certificate")))
    }

    @Test
    fun getCertificateByNameReturnsExistingCertificate() {
        val certificateEntity = CertificateEntity(1, "Clark", "Kent", false)

        certificateRepository.save(certificateEntity)

        mvc.perform(MockMvcRequestBuilders.get("/certificates?name=Clark").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id", `is`(certificateEntity.id)))
    }

    @Test
    fun getAllCertificatesReturnsEmptyArrayWhenNoCertificates() {
        mvc.perform(MockMvcRequestBuilders.get("/certificates").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }
}
