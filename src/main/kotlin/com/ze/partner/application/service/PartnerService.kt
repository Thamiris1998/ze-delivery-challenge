package com.ze.partner.application.service

import com.ze.partner.application.domain.Partner
import com.ze.partner.application.model.PartnerModel
import com.ze.partner.application.repository.PartnerRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PartnerService(private val partnerRepository: PartnerRepository) {

    fun findById(id: String): Partner? {
        return partnerRepository.findById(UUID.fromString(id))
    }

    fun save(model: PartnerModel): Partner {
        return partnerRepository.save(Partner.of(model))
    }

}