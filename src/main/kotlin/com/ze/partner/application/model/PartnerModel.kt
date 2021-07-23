package com.ze.partner.application.model

import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class  PartnerModel (
        @field:NotNull(message = "{field.not.null}")
        val tradingName: String,
        @field:NotNull(message = "{field.not.null}")
        val ownerName: String,
        @field:NotNull(message = "{field.not.null}")
        val document: String,
        @field:Valid
        val coverageArea: GeoJsonMultiPolygon,
        @field:Valid
        val address: GeoJsonPoint,
)