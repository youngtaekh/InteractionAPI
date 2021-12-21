package com.young.streaming.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/test"])
class TestController {

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
}
