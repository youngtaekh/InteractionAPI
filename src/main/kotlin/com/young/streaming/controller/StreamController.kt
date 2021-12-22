package com.young.streaming.controller

import com.young.streaming.model.StreamModel
import com.young.streaming.service.StreamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/stream"])
class StreamController {
    @Autowired
    private lateinit var streamService: StreamService

    @GetMapping
    fun getStreams(@RequestParam(required = false) master: String?): ResponseEntity<Any> {
        val time = System.currentTimeMillis()
        return if (master.isNullOrEmpty()) {
            println("GET streams")
            ResponseEntity.ok().body(streamService.getAllStreams())
        } else {
            println("GET stream by $master")
            ResponseEntity.ok().body(streamService.getStreamByMaster(master))
        }
    }

    @GetMapping("/{count}")
    fun getStreams(@PathVariable count: Int): ResponseEntity<Any> {
        println("GET streams $count")
        return ResponseEntity.ok().body(streamService.getAllStreams())
    }

    @PutMapping("/put")
    fun put(): ResponseEntity<String> {
        println("PUT")
        return ResponseEntity.ok("PUT OK")
    }

    @PostMapping
    fun saveStream(@RequestBody streamModel: StreamModel): ResponseEntity<Any> {
        println(streamModel)
        val model = streamService.saveStream(streamModel)
        println(model)
        return ResponseEntity.ok().body(true)
    }

    @DeleteMapping("/{streamKey}")
    fun deleteStream(@PathVariable streamKey: String): ResponseEntity<Any> {
        println("DELETE stream $streamKey")
        streamService.removeStreamByKey(streamKey)
        return ResponseEntity.ok().body(true)
    }

    @DeleteMapping
    fun deleteAllStreams(): ResponseEntity<Any> {
        println("DELETE all streams")
        streamService.deleteAllStreams()
        return ResponseEntity.ok().body(true)
    }
}
