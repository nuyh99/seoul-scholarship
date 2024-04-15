package org.seoul.morlatjanghak.ai

import org.seoul.morlatjanghak.ai.dto.ChatResponse

object AIMessageParser {

    fun parseResponse(result: String) = ChatResponse(
        result.replace("\\\\", "\\")
    )
}