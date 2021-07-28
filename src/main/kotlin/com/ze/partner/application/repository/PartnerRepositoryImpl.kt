package com.ze.partner.application.repository

import com.ze.partner.application.domain.Partner
import com.ze.partner.infrastructure.exception.PartnerAlreadyExistsException
import com.ze.partner.infrastructure.exception.PartnerNotFoundException
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class PartnerRepositoryImpl(private val mongoTemplate: MongoTemplate) : PartnerRepository {

    override fun save(partner: Partner): Partner {
        val existsDocument = alreadyExistsDocument(partner.document)
       return if(existsDocument) throw PartnerAlreadyExistsException(partner.document) else mongoTemplate.save(partner)
    }
    override fun findById(id: String): Partner {
        return mongoTemplate.findById(id,Partner::class.java) ?: throw PartnerNotFoundException(id)
    }

    override fun findNearest(long: Double, lat: Double): Partner {
        val query = Query().addCriteria(
            Criteria
                .where("address").nearSphere(Point(long, lat))
                .and("coverageArea").intersects(GeoJsonPoint(long, lat)))

        return mongoTemplate.findOne(query, Partner::class.java) ?: throw PartnerNotFoundException("")
    }

    override fun alreadyExistsDocument(document: String): Boolean {
        val query = Query().addCriteria(Criteria.where("document").`is`(document))
        val partner = mongoTemplate.findOne(query, Partner::class.java)
        return partner != null
    }
}