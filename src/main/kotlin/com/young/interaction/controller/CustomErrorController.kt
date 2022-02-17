package com.young.interaction.controller

import com.young.interaction.model.response.Response
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@RestController
class CustomErrorController: ErrorController {
    @RequestMapping(value = ["/error1"])
    fun handleError(http: HttpServletRequest): ResponseEntity<Any> {
        val status = http.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
        if (status != null) {
            val statusCode = Integer.valueOf(status.toString())
            println(statusCode)
        }
        println("error")
        return Response.error("error")
    }
}
