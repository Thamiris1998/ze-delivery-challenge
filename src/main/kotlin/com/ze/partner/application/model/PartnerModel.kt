package com.ze.partner.application.model

import com.ze.partner.infrastructure.util.Document
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class PartnerModel (
        @field:NotNull(message = "{field.not.null}") val tradingName: String,
        @field:NotNull(message = "{field.not.null}") val ownerName: String,
        @Document
        val document: String,
        @Valid val coverageArea: GeoJsonMultiPolygon,
        @Valid val address: GeoJsonPoint
)