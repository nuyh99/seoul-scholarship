package org.seoul.morlatjanghak.scholarship.dto

import org.seoul.morlatjanghak.appliedscholarship.ApplyingStatus
import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import java.time.LocalDate
import java.time.LocalDateTime

data class StoredScholarshipResponse(
    val id: Long,
    val organization: String,
    val productName: String,
    val supportDetails: String,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val viewCount: Long,
    val applyingStatus: ApplyingStatus,
    val storedDate: LocalDateTime,
) {
    companion object {
        fun of(
            scholarship: Scholarship,
            applyingStatus: ApplyingStatus = ApplyingStatus.NOTHING,
            storedDate: LocalDateTime
        ): StoredScholarshipResponse {
            return StoredScholarshipResponse(
                id = scholarship.id,
                organization = scholarship.organization,
                productName = scholarship.productName,
                supportDetails = scholarship.supportDetails,
                startDate = scholarship.startDate,
                endDate = scholarship.endDate,
                viewCount = scholarship.viewCount,
                applyingStatus = applyingStatus,
                storedDate = storedDate
            )
        }
    }
}
