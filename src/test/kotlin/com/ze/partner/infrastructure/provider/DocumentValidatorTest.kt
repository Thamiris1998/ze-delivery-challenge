package com.ze.partner.infrastructure.provider

import com.github.javafaker.Faker
import com.ze.partner.infrastructure.extensions.validCNPJ
import com.ze.partner.infrastructure.extensions.validCPF
import com.ze.partner.infrastructure.util.DocumentValidator
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.validation.ConstraintValidatorContext

class DocumentValidatorTest {

    private val documentValidator = DocumentValidator()

    private val constraintValidatorContext = mockk<ConstraintValidatorContext>()

    @Test
    fun `given incorrect CPF then return false`() {
        val faker = Faker()
        val response = documentValidator.isValid(faker.number().numberBetween(11,11).toString(),constraintValidatorContext)
        Assertions.assertFalse(response)
    }

    @Test
    fun `given correct CPF than return true`() {
        val response = documentValidator.isValid(Faker().validCPF(),constraintValidatorContext)
        Assertions.assertTrue(response)
    }

    @Test
    fun `given incorrect CNPJ then return false`() {
        val faker = Faker()
        val response = documentValidator.isValid(faker.number().numberBetween(14,14).toString(),constraintValidatorContext)
        Assertions.assertFalse(response)
    }

    @Test
    fun `given correct CNPJ then return true`() {
        val response = documentValidator.isValid(Faker().validCNPJ(),constraintValidatorContext)
        Assertions.assertTrue(response)
    }

}