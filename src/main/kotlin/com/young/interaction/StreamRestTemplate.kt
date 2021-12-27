package com.young.interaction

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset

class StreamRestTemplate {
    companion object {
        private const val address = "https://api.mux.com/video/v1/live-streams"
        private const val tokenId = "b0be692e-945b-4c7f-b18b-41c40f77d09b"
        private const val secretKey = "/VlZJvKfEhw3qCtRfBSn+gWSZCDuOV5GtDKp+5tyzjJZdIpy+KQ9imNKo6nD7BDaZg180tb3TfO"
        private const val plainCred = "$tokenId:$secretKey"
    }

    fun postStream(): String? {
        val param = createLiveBody()
        println(param)
        val entity = HttpEntity(param, getHttpHeaders())
        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.exchange(address, HttpMethod.POST, entity, String::class.java)

        println("createStream ${responseEntity.statusCode}")
        return responseEntity.body
    }

    fun getStreams(): String? {
        val entity = HttpEntity(null, getHttpHeaders())
        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.exchange(address, HttpMethod.GET, entity, String::class.java)

        println("getStreams ${responseEntity.statusCode}")
        return responseEntity.body
    }

    fun retrieveStream(streamId: String): String? {
        val entity = HttpEntity(null, getHttpHeaders())
        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.exchange("$address/$streamId", HttpMethod.GET, entity, String::class.java)

        println("retrieveStream ${responseEntity.statusCode}")
        return responseEntity.body
    }

    fun disableStream(streamId: String): Int {
        val entity = HttpEntity(null, getHttpHeaders())
        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.exchange("$address/$streamId/disable", HttpMethod.PUT, entity, String::class.java)

        println("retrieveStream ${responseEntity.statusCode}")
        return 1
    }

    fun enableStream(streamId: String): Int {
        val entity = HttpEntity(null, getHttpHeaders())
        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.exchange("$address/$streamId/enable", HttpMethod.PUT, entity, String::class.java)

        println("retrieveStream ${responseEntity.statusCode}")
        return 1
    }

    fun deleteStream(streamId: String): String? {
        val entity = HttpEntity(null, getHttpHeaders())
        val restTemplate = RestTemplate()
        val responseEntity = restTemplate.exchange("$address/$streamId", HttpMethod.DELETE, entity, String::class.java)

        println("deleteStream ${responseEntity.statusCode}")
        return responseEntity.body
    }

    private fun getCredential(): String {
        val plainCredBytes = plainCred.encodeToByteArray()
        val base64CredBytes = Base64.encodeBase64(plainCredBytes)
        return String(base64CredBytes)
    }

    private fun getHttpHeaders(): HttpHeaders {
        val base64Cred = getCredential()

        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType("application", "json", Charset.forName("UTF-8"))
        httpHeaders.add("Authorization", "Basic $base64Cred")

        return httpHeaders
    }

    private fun createLiveBody(): String {
        val objectMapper = ObjectMapper()
        val bodyMap = HashMap<String, Any>()

        val policyArray = arrayListOf("public")
        bodyMap["playback_policy"] = policyArray

        val settingMap = HashMap<String, Any>()
        settingMap["playback_policy"] = policyArray
        bodyMap["new_asset_settings"] = settingMap

        return objectMapper.writeValueAsString(bodyMap)
    }
}
