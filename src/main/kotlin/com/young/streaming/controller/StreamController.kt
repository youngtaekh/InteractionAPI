package com.young.streaming.controller

import com.young.streaming.StreamRestTemplate
import com.young.streaming.model.StreamModel
import com.young.streaming.service.StreamService
import com.young.streaming.util.ResultUtil
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
        return if (master.isNullOrEmpty()) {
            println("GET streams")
            val result = StreamRestTemplate().getStreams()
            println(result)
            ResponseEntity.ok().body(streamService.getAllStreams())
        } else {
            println("GET stream by $master")
            ResponseEntity.ok().body(streamService.getStreamByMaster(master))
        }
    }

    @GetMapping("/{streamId}")
    fun getStream(@PathVariable streamId: String): ResponseEntity<Any> {
        println("GET streams $streamId")
        val result = StreamRestTemplate().retrieveStream(streamId)
        println(result)

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
        val result = StreamRestTemplate().postStream()
        println(result)

        if (ResultUtil().setPostStream(streamModel, result) == 1) {
            println("Success set model")
        } else {
            println("Failure set model")
        }

        println(streamModel)
        val model = streamService.saveStream(streamModel)
        println(model)

        return ResponseEntity.ok().body(true)
    }

    @PutMapping("/enable/{streamId}")
    fun enableStream(@PathVariable streamId: String): ResponseEntity<Any> {
        val result = StreamRestTemplate().enableStream(streamId)
        return ResponseEntity.ok(true)
    }

    @PutMapping("/disable/{streamId}")
    fun disableStream(@PathVariable streamId: String): ResponseEntity<Any> {
        val result = StreamRestTemplate().disableStream(streamId)
        return ResponseEntity.ok().body(true)
    }

    @DeleteMapping("/{streamId}")
    fun deleteStream(@PathVariable streamId: String): ResponseEntity<Any> {
        println("DELETE stream $streamId")
        val result = StreamRestTemplate().deleteStream(streamId)
        println("deleteStream result: $result")
        streamService.removeStreamById(streamId)
        return ResponseEntity.ok().body(true)
    }

    @DeleteMapping
    fun deleteAllStreams(): ResponseEntity<Any> {
        println("DELETE all streams")
        streamService.deleteAllStreams()
        return ResponseEntity.ok().body(true)
    }
}
