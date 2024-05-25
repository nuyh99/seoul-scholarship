package org.seoul.morlatjanghak.admin.dto

import org.seoul.morlatjanghak.admin.domain.UploadStatus
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class UploadResponse(
    val number: Int,
    val fileName: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val requestDate: LocalDateTime,
    val completed: UploadStatus,
)
