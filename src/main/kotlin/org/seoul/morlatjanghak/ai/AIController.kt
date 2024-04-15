package org.seoul.morlatjanghak.ai

import org.seoul.morlatjanghak.ai.client.AIClient
import org.seoul.morlatjanghak.ai.dto.ChatRequest
import org.seoul.morlatjanghak.ai.dto.ChatResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class AIController(
    private val aiClient: AIClient
) {

    private val aiMessageParser: AIMessageParser = AIMessageParser

    @GetMapping("/v1/ai")
    fun index(): String = "chat"

    @PostMapping("/v1/ai")
    fun chat(@RequestBody request: ChatRequest): ResponseEntity<ChatResponse> {
        val result = aiClient.chat(request.toAIChatRequest())
        val response = aiMessageParser.parseResponse(result)

        return ResponseEntity.ok(response)
    }
}