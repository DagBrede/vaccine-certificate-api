package com.vaccinecertificate.integration.example.client;

import com.vaccinecertificate.integration.example.dto.Example
import com.fasterxml.jackson.databind.DeserializationFeature
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.jackson.defaultMapper
import org.springframework.stereotype.Service;

@Service
class ExampleClient {
    private val mapper = defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    private val BASE_URL = "baseUrl"

    fun get(id: Int): Example {
        val example = makeGetRequest("$BASE_URL/$id")
        return mapper.readValue(example, Example::class.java)
    }

    private fun makeGetRequest(url: String): String {
            val (_, _, result) = url.httpGet().responseString()
            val (body, error) = result

            return body ?: throw Exception("HTTP ERROR: $error")
    }
}
