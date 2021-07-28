package com.ze.partner.infrastructure.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors

@ControllerAdvice
class HandlerException : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorMessages = ex.bindingResult
            .allErrors
            .stream()
            .map { e: ObjectError ->
                e.defaultMessage.toString()
            }
            .collect(Collectors.toList())
        return ResponseEntity(errorMessages, status)
    }

    @ExceptionHandler(PartnerNotFoundException::class)
    fun partnerNotFoundException(ex: PartnerNotFoundException, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity<Any>(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(PartnerAlreadyExistsException::class)
    fun partnerAlreadyExistsException(ex: PartnerAlreadyExistsException, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity<Any>(ex.message, HttpStatus.CONFLICT)
    }

}