package com.ze.partner.application.repository

import com.ze.partner.application.domain.Partner
import org.springframework.stereotype.Repository

@Repository
interface PartnerRepository {
    fun save(partner: Partner) : Partner
    fun findById(id: String) : Partner
    fun alreadyExistsDocument(document: String): Boolean
    fun findNearest(long: Double, lat: Double) : Partner
}