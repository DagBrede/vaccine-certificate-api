package com.vaccinecertificate.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class WebSecurityConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry?) {
        registry!!
            .addMapping("/**")
            .allowedMethods("*")


    }
}
