package com.ze.partner.infrastructure.util

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [(DocumentValidator::class)])
@MustBeDocumented
annotation class Document(
    val message: String = "Please, insert valid document",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)