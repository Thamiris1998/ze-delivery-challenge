package com.ze.partner.infrastructure.util

import br.com.caelum.stella.validation.CNPJValidator
import br.com.caelum.stella.validation.CPFValidator
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class DocumentValidator : ConstraintValidator<Document, String> {
    override fun isValid(document: String, context: ConstraintValidatorContext) : Boolean {
        val documentNumber = extractNumbers(document)

        return when (PrincipalDocumentType.ofSize(documentNumber.length)) {
            PrincipalDocumentType.CPF -> validateCpf(documentNumber)
            PrincipalDocumentType.CNPJ -> validateCnpj(documentNumber)
            else -> false
        }
    }

    private fun extractNumbers(document: String) = document.replace("[\\D]".toRegex(), "")

    private fun validateCpf(documentNumber: String): Boolean {
        runCatching { CPFValidator().assertValid(documentNumber) }
            .getOrElse {
                return false
            }
        return true
    }

    private fun validateCnpj(documentNumber: String): Boolean {
        runCatching { CNPJValidator().assertValid(documentNumber) }
            .getOrElse {
                return false
            }
        return true
    }
}

enum class PrincipalDocumentType(val size: Int) {
    CPF(11), CNPJ(14);

    companion object {
        fun ofSize(size: Int) = values()
            .firstOrNull { it.size == size }
    }
}