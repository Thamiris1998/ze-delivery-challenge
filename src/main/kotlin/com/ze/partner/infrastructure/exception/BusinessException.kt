package com.ze.partner.infrastructure.exception

import java.lang.RuntimeException

open class BusinessException(message: String) : RuntimeException(message) {
    val code = javaClass
        .simpleName
        .replace("Exception", "")
        .replace("([a-z])([A-Z]+)".toRegex(), "$1_$2")
        .toUpperCase()
}
