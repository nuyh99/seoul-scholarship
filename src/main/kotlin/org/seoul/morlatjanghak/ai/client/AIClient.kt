package org.seoul.morlatjanghak.ai.client

import org.seoul.morlatjanghak.ai.dto.ChatRequest
import org.seoul.morlatjanghak.ai.dto.ChatResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange
interface AIClient {

    @PostExchange
    fun chat(@RequestBody data: ChatRequest): ChatResponse
}
