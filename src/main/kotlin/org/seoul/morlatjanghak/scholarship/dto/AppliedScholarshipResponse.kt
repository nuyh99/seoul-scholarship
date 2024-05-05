package org.seoul.morlatjanghak.scholarship.dto

import org.seoul.morlatjanghak.appliedscholarship.AppliedScholarship
import org.seoul.morlatjanghak.appliedscholarship.ApplyingStatus
import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import java.time.LocalDate
import java.time.LocalDateTime

data class AppliedScholarshipResponse(
    val id: Long,
    val organization: String,
    val productName: String,
    val supportDetails: String,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val viewCount: Long,
    val applyingStatus: ApplyingStatus,
    val storedDate: LocalDateTime?,
    val modifiedDate: LocalDateTime
) {
    companion object {
        fun of(
            scholarship: Scholarship,
            applied: AppliedScholarship,
            storedDate: LocalDateTime?
        ): AppliedScholarshipResponse {
            return AppliedScholarshipResponse(
                id = scholarship.id,
                organization = scholarship.organization,
                productName = scholarship.productName,
                supportDetails = scholarship.supportDetails,
                startDate = scholarship.startDate,
                endDate = scholarship.endDate,
                viewCount = scholarship.viewCount,
                applyingStatus = applied.status,
                storedDate = storedDate,
                modifiedDate = applied.updatedAt!!
            )
        }
    }
}
