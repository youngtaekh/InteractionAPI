package com.young.interaction.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/test"])
class TestController {
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @GetMapping
    fun test(): ResponseEntity<String> {
        println("GET test")
        return ResponseEntity.ok("OK")
    }

    @PutMapping("/put")
    fun put(): ResponseEntity<String> {
        println("PUT")
        return ResponseEntity.ok("PUT OK")
    }

    @GetMapping("/template")
    fun templateTest(): ResponseEntity<Any> {
        println("GET templateTest")
        return ResponseEntity.ok("OK")
    }
}
