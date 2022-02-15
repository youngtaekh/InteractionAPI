package com.young.interaction.model.response

import org.springframework.http.ResponseEntity

data class Response<T>(
    val header: Header,
    val body: T
) {
    data class Header(
        val status: Int,
        val code: Int,
        val description: String
    )

    companion object {
        fun <T> success(body: T): ResponseEntity<Any> {
            return ResponseEntity.ok(Response(Header(1, 0, "Success"), body))
        }

        fun <T> error(body: T): ResponseEntity<Any>{
            return ResponseEntity.ok(Response(Header(0, 10000, "Error"), body))
        }
    }
}
