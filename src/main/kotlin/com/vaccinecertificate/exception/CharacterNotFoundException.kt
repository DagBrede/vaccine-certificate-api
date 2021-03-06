package com.vaccinecertificate.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Exception thrown when a requested certificate can't be found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find certificate")
class CertificateNotFoundException(message: String) : RuntimeException(message)
