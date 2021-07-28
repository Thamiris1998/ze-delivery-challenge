package com.ze.partner.infrastructure.provider

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.mongodb.core.geo.GeoJsonModule

object ObjectMapperProvider {
    fun provide() = jacksonObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE

        setDefaultPrettyPrinter(
            DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            }
        )
        registerModule(JavaTimeModule())

        enable(SerializationFeature.INDENT_OUTPUT)
        enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)

        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        registerModule(GeoJsonModule())
    }

    fun readJsonResource(fileName: String) = ClassLoader.getSystemResource("json/$fileName.json").readText()

}