package com.ze.partner.infrastructure.exception

import java.lang.RuntimeException

class PartnerNotFoundException(message: String) : RuntimeException() {
    override val message = "Partner not found $message"
}

