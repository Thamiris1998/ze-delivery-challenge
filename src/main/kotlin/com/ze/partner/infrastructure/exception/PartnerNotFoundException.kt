package com.ze.partner.infrastructure.exception

import org.slf4j.LoggerFactory
import java.util.UUID

class PartnerNotFoundException private constructor (message: String) : BusinessException(message) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)

        fun of(id: UUID): PartnerNotFoundException {
            val message = "PartnerId $id does not exist"
            logger.error(message)
            return PartnerNotFoundException(message)
        }
    }
}