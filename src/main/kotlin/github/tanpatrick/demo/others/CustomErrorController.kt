package github.tanpatrick.demo.others

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@RestController
class CustomErrorController : ErrorController {

    @GetMapping("/error")
    fun handleError(request: HttpServletRequest): ResponseEntity<ErrorResponseDTO> {
        val statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
            .let {
                HttpStatus.valueOf(it.toString().toInt())
            }

        val body = when (statusCode) {
            HttpStatus.NOT_FOUND -> ApiErrorResponse.PathNotFound
            else -> ApiErrorResponse.InternalServerError
        }

        return ResponseEntity
            .status(statusCode)
            .body(body)
    }
}


