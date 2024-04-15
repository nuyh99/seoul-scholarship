package org.seoul.morlatjanghak.ai.dto

import org.seoul.morlatjanghak.ai.client.ChatRequest

data class ChatRequest(
    val text: String = ""
){

    fun toAIChatRequest(): ChatRequest = ChatRequest(inputs = text)
}
