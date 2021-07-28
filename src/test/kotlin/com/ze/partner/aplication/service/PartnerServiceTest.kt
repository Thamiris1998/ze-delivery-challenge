package com.ze.partner.aplication.service

import com.ze.partner.application.domain.Partner
import com.ze.partner.application.model.PartnerModel
import com.ze.partner.application.repository.PartnerRepository
import com.ze.partner.application.service.PartnerService
import com.ze.partner.infrastructure.exception.PartnerAlreadyExistsException
import com.ze.partner.infrastructure.exception.PartnerNotFoundException
import com.ze.partner.infrastructure.provider.ObjectMapperProvider
import com.ze.partner.infrastructure.provider.ObjectMapperProvider.readJsonResource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class PartnerServiceTest {

    private val partnerRepository = mockk<PartnerRepository>()
    private val partnerService = PartnerService(partnerRepository)
    private val mapper = ObjectMapperProvider.provide()
    private var model = mapper.readValue(readJsonResource("partner/pinheiros"), PartnerModel::class.java)
    private val partner = Partner.of(model)

    @Test
    fun `given correct partner then partnerRepository_save() must be called once`() {
        every { partnerRepository.save(partner) } returns partner
        partnerService.save(model)
        verify(exactly = 1) { partnerRepository.save(partner) }
    }

    @Test
    fun `given incorrect partner_id then return throw PartnerNotFoundException`() {
        val partnerId = UUID.randomUUID().toString()
        every { partnerRepository.findById(partnerId) } throws PartnerNotFoundException(partnerId)
        assertThrows<PartnerNotFoundException> {
            partnerService.findById(partnerId)
        }
    }

    @Test
    fun `given a partner_id already exist then return throw PartnerAlreadyExistsException`() {
        every { partnerRepository.save(partner) } throws PartnerAlreadyExistsException(partner.document)
        assertThrows<PartnerAlreadyExistsException> {
            partnerService.save(model)
        }
    }

}