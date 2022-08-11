package github.tanpatrick.demo.others

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleInvalidParameterType() = ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ApiErrorResponse.InvalidParameterType)

    @ExceptionHandler(RecordNotFound::class)
    fun handleRecordNotFound(ex: RecordNotFound) = ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ApiErrorResponse.RecordNotFound)

    @ExceptionHandler(Exception::class)
    fun handleAllOtherExceptions(ex: Exception) = ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiErrorResponse.InternalServerError)
}

