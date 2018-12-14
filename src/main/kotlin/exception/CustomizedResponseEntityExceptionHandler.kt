package exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

/**
 * @author vinicius.moraes on 8/21/18
 */
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {

        val exceptionResponse = ExceptionResponse(Date(), "Validation Failed", ex.bindingResult.toString())

        return ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST)
    }

}