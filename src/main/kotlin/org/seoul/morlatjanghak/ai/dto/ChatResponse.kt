package org.seoul.morlatjanghak.ai.dto

data class ChatResponse(
    var choices: List<Choice>,
) {
    fun getContent(): String {
        return choices.joinToString { it.getContent() }
    }
}

data class Choice(
    var message: Message,
) {

    fun getContent(): String {
        return message.content
    }
}
