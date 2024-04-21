package org.seoul.morlatjanghak.ai.dto

data class Message(
    var role: String,
    var content: String
) {
    override fun toString(): String {
        return "Message(role='$role', content='$content')"
    }
}
