package org.seoul.morlatjanghak.ai.client

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange
interface AIClient {

    @PostExchange("/chat", contentType = "application/json")
    fun chat(@RequestBody text: ChatRequest): String
}

data class ChatRequest(
    val inputs: String
)
