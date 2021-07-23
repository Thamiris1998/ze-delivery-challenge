package com.ze.partner.application.repository

import com.ze.partner.application.domain.Partner
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PartnerRepository {
    fun save( partner: Partner) : Partner
    fun findById( id: UUID) : Partner
}