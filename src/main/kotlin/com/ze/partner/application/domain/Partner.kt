package com.ze.partner.application.domain

import com.ze.partner.application.model.PartnerModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "partner")
data class Partner (
    @Id
    val id: UUID?= null,
    val tradingName: String,
    val ownerName: String,
    @Indexed(unique = true)
    private val document: String,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private val coverageArea: GeoJsonMultiPolygon? = null,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private val address: GeoJsonPoint? = null
){
    companion object {
        fun of(model: PartnerModel): Partner {
           return  Partner(
                tradingName = model.tradingName,
                ownerName = model.ownerName,
                document = model.document,
                coverageArea = model.coverageArea,
                address = model.address
            )
        }
    }
}