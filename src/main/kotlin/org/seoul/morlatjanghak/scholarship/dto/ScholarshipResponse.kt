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
    var applyingStatus: ApplyingStatus? = ApplyingStatus.NOTHING,
    var isStored: Boolean? = false,
) {

    fun updateStored(isStored: Boolean) {
        this.isStored = isStored
        if (applyingStatus == ApplyingStatus.NOTHING) {
            applyingStatus = ApplyingStatus.SAVED
        }
    }

    fun updateApplyingStatus(applyingStatus: ApplyingStatus) {
        this.applyingStatus = applyingStatus
    }

    companion object {
        fun of(
            scholarship: Scholarship,
        ): ScholarshipResponse {
            val response = ScholarshipResponse(
                id = scholarship.id,
                organization = scholarship.organization,
                productName = scholarship.productName,
                supportDetails = scholarship.formattedSupportDetails,
                startDate = scholarship.startDate,
                endDate = scholarship.endDate,
                viewCount = scholarship.viewCount,
            )

            return response
        }
    }
}
