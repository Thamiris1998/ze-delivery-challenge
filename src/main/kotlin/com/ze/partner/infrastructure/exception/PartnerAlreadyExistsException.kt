package com.ze.partner.infrastructure.exception

import java.lang.RuntimeException

class PartnerAlreadyExistsException(document: String) : RuntimeException() {
    override val message = "Partner document $document already exists"
}