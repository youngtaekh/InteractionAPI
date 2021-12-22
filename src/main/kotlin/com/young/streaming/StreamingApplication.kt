package com.young.streaming

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableJpaAuditing
class StreamingApplication

fun main(args: Array<String>) {
	runApplication<StreamingApplication>(*args)
}

@PostConstruct
fun setTimeZone() {
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
}

