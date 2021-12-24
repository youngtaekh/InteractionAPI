package com.young.streaming.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset

@RestController
@RequestMapping(value = ["/test"])
class TestController {
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private fun restTemplateTest() {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType("application", "json", Charset.forName("UTF-8"))

        val map = HashMap<String, Any>()
        map["msg"] = "Send Request"
        val param1 = objectMapper.writeValueAsString(map)
        println("restTemplateTest $param1")

        val entity = HttpEntity(param1, httpHeaders)
        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.exchange("http://localhost:8080/test/template", HttpMethod.GET, entity, String::class.java)

        println("restTemplateTest ${responseEntity.statusCode}")
        println("restTemplateTest ${responseEntity.body}")
    }

    @GetMapping
    fun test(): ResponseEntity<String> {
        println("GET test")
        restTemplateTest()
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
