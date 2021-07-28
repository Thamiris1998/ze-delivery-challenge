package com.ze.partner.application.domain

import com.ze.partner.application.model.PartnerModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "partner")
data class Partner (
    @Id
    val id: String?= null,
    val tradingName: String,
    val ownerName: String,
    @Indexed(unique = true)
    val document: String,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    val coverageArea: GeoJsonMultiPolygon,
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    val address: GeoJsonPoint
){
    companion object {
        fun of(model: PartnerModel): Partner {
           return  Partner(
                tradingName = model.tradingName,
                ownerName = model.ownerName,
                document = unMask(model.document),
                coverageArea = model.coverageArea,
                address = model.address
            )
        }

        private fun unMask(s: String): String {
            return s.replace("[^0-9]*".toRegex(), "")
        }
    }
}