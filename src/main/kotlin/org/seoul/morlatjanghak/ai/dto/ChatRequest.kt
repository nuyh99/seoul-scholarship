package org.seoul.morlatjanghak.ai.dto

data class ChatRequest(
    var model: String,
    var messages: List<Message>
) {
    override fun toString(): String {
        return "ChatRequest(model='$model', messages=$messages)"
    }
}
