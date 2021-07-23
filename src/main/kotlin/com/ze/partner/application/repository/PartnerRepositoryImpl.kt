package com.ze.partner.application.repository

import com.ze.partner.application.domain.Partner
import com.ze.partner.infrastructure.exception.PartnerNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findById
import org.springframework.stereotype.Component
import java.util.*

@Component
class PartnerRepositoryImpl(private val mongoTemplate: MongoTemplate) : PartnerRepository {

    override fun save(partner: Partner): Partner {
        //TODO verificar se já existe document
       return mongoTemplate.save(partner)
    }

    override fun findById(id: UUID): Partner {
        return mongoTemplate.findById(id) ?: throw PartnerNotFoundException.of(id)
    }
}