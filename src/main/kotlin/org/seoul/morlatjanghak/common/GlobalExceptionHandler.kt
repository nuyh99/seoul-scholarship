package org.seoul.morlatjanghak.common

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handle(e: IllegalArgumentException): ResponseEntity<String> = ResponseEntity.badRequest().body(e.message)

    @ExceptionHandler(Exception::class)
    fun handle(e: Exception): ResponseEntity<String> = ResponseEntity.internalServerError().body(e.stackTraceToString())
}
