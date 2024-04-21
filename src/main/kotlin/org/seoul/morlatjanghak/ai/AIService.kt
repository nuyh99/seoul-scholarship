package org.seoul.morlatjanghak.ai

import org.seoul.morlatjanghak.ai.client.AIClient
import org.seoul.morlatjanghak.ai.dto.ChatRequest
import org.seoul.morlatjanghak.ai.dto.Message
import org.springframework.stereotype.Service

@Service
class AIService(private val client: AIClient) {

    fun chat(request: String): String {
        val message = Message(role = USER_ROLE, content = request)
        return client.chat(
            ChatRequest(model = CHAT, messages = listOf(SYSTEM_PROMPT, message))
        ).getContent()
    }

    companion object {
        private const val CHAT = "solar-1-mini-chat"
        private val SYSTEM_PROMPT = Message("system", "안녕하세요! 무엇을 도와드릴까요?")
        private const val USER_ROLE = "user"
    }
}