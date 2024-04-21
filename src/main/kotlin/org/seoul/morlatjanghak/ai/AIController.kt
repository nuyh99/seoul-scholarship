package org.seoul.morlatjanghak.ai

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class AIController(
    private val aiService: AIService
) {
    
    @GetMapping("/v1/ai")
    fun index(): String = "chat"

    @PostMapping("/v1/ai")
    fun chat(@RequestBody text: String): ResponseEntity<String> {
        val response = aiService.chat(text)
        return ResponseEntity.ok(response)
    }
}