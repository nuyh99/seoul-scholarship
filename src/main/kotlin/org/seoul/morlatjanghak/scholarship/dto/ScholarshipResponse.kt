package org.seoul.morlatjanghak.scholarship.dto

import org.seoul.morlatjanghak.scholarship.Scholarship
import java.time.LocalDate

data class ScholarshipResponse(
    val id: Long,
    val organization: String,
    val productName: String,
    val supportDetails: String,
    val endDate: LocalDate?,
    val viewCount: Long,
) {
    companion object {
        fun from(scholarship: Scholarship): ScholarshipResponse {
            return ScholarshipResponse(
                scholarship.id,
                scholarship.organization,
                scholarship.productName,
                scholarship.supportDetails,
                scholarship.endDate,
                scholarship.viewCount,
            )
        }
    }
}
