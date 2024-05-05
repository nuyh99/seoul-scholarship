package org.seoul.morlatjanghak.scholarship.dto

import org.seoul.morlatjanghak.appliedscholarship.ApplyingStatus
import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import java.time.LocalDate

data class ScholarshipResponse(
    val id: Long,
    val organization: String,
    val productName: String,
    val supportDetails: String,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val viewCount: Long,
    var applyingStatus: ApplyingStatus,
    var isStored: Boolean,
) {

    fun updateStored(isStored: Boolean) {
        this.isStored = isStored
    }

    fun updateApplyingStatus(applyingStatus: ApplyingStatus) {
        this.applyingStatus = applyingStatus
    }

    companion object {
        fun of(
            scholarship: Scholarship,
            applyingStatus: ApplyingStatus = ApplyingStatus.NOTHING,
            isStored: Boolean = false
        ): ScholarshipResponse {
            val response = ScholarshipResponse(
                id = scholarship.id,
                organization = scholarship.organization,
                productName = scholarship.productName,
                supportDetails = scholarship.supportDetails,
                startDate = scholarship.startDate,
                endDate = scholarship.endDate,
                viewCount = scholarship.viewCount,
                applyingStatus = applyingStatus,
                isStored = isStored,
            )

            if (response.isStored && response.applyingStatus == ApplyingStatus.NOTHING) {
                response.applyingStatus = ApplyingStatus.SAVED
            }

            return response
        }
    }
}
