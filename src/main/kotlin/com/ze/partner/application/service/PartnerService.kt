package com.ze.partner.application.service

import com.ze.partner.application.domain.Partner
import com.ze.partner.application.model.PartnerModel
import com.ze.partner.application.repository.PartnerRepository
import org.springframework.stereotype.Service

@Service
class PartnerService(private val partnerRepository: PartnerRepository) {

    fun findNearest(long: Double, lat: Double): Partner {
        return partnerRepository.findNearest(long, lat)
    }

    fun findById(id: String): Partner {
        return partnerRepository.findById(id)
    }

    fun save(model: PartnerModel): Partner {
        return partnerRepository.save(Partner.of(model))
    }

}