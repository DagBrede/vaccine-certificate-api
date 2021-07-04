package com.vaccinecertificate.service

import com.vaccinecertificate.domain.CertificateEntity
import com.vaccinecertificate.exception.CertificateNotFoundException
import com.vaccinecertificate.repository.CertificateRepository
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Tests for {@link CertificateService}.
 */
@RunWith(MockitoJUnitRunner::class)
class CertificateServiceTest {

    @InjectMocks
    lateinit var certificateService: CertificateService

    @Mock
    lateinit var certificateRepository: CertificateRepository

    @Test
    fun getByIdInvokesRepository() {
        `when`(certificateRepository.findById(1))
            .thenReturn(Optional.of(CertificateEntity(1, "Bruce", "Wayne", true)))

        certificateService.getById(1)

        verify(certificateRepository).findById(1)
    }

    @Test
    fun getByIdConvertsAndReturnsResult() {
        val certificateEntity = CertificateEntity(1, "Clark", "Kent", false)
        `when`(certificateRepository.findById(1)).thenReturn(Optional.of(certificateEntity))

        val retrievedCertificate = certificateService.getById(1)

        assertNotNull(retrievedCertificate)
        assertEquals(1, retrievedCertificate.id)
        assertEquals("Clark", retrievedCertificate.firstName)
    }

    @Test
    fun getByIdThrowsExceptionWhenElementNotFound() {
        assertThrows<CertificateNotFoundException>("Certificate with id 1 not found") {
            certificateService.getById(1)
        }
    }

    @Test
    fun getAllCertificatesInvokesRepository() {
        `when`(certificateRepository.findAll()).thenReturn(emptyList())

        certificateService.getAllCertificates()

        verify(certificateRepository).findAll()
    }
}
