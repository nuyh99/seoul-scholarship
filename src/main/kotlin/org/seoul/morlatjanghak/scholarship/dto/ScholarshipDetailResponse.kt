package org.seoul.morlatjanghak.scholarship.dto

import org.seoul.morlatjanghak.appliedscholarship.ApplyingStatus
import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import java.time.LocalDate

data class ScholarshipDetailResponse(
    val id: Long,
    val organization: String,
    val productName: String,
    val organizationType: String,
    val scholarshipType: String,
    val universityCategory: String,
    val grade: String,
    val majorCategory: String,
    val gradeDetails: String,
    val incomeDetails: String,
    val supportDetails: String,
    val formattedSupportDetails: String,
    val specificQualificationDetails: String,
    val localResidencyDetails: String,
    val selectionMethodDetails: String,
    val selectionCountDetails: String,
    val eligibilityRestrictionDetails: String,
    val recommendationRequiredDetails: String,
    val requiredDocumentDetails: String,
    val homePageUrl: String,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val viewCount: Long,
    val effortLevel: Int,
    val effortLabel: String,
    var applyingStatus: ApplyingStatus? = ApplyingStatus.NOTHING,
    var isStored: Boolean? = false,
) {
    companion object {
        fun from(
            scholarship: Scholarship,
            isStored: Boolean,
            applyingStatus: ApplyingStatus
        ): ScholarshipDetailResponse {
            return ScholarshipDetailResponse(
                id = scholarship.id,
                organization = scholarship.organization,
                productName = scholarship.productName,
                organizationType = scholarship.organizationType,
                scholarshipType = scholarship.scholarshipType,
                universityCategory = scholarship.universityCategory,
                grade = scholarship.grade,
                majorCategory = scholarship.majorCategory,
                gradeDetails = scholarship.gradeDetails,
                incomeDetails = scholarship.incomeDetails,
                supportDetails = scholarship.supportDetails,
                formattedSupportDetails = scholarship.formattedSupportDetails,
                specificQualificationDetails = scholarship.specificQualificationDetails,
                localResidencyDetails = scholarship.localResidencyDetails,
                selectionMethodDetails = scholarship.selectionMethodDetails,
                selectionCountDetails = scholarship.selectionCountDetails,
                eligibilityRestrictionDetails = scholarship.eligibilityRestrictionDetails,
                recommendationRequiredDetails = scholarship.recommendationRequiredDetails,
                requiredDocumentDetails = scholarship.requiredDocumentDetails,
                homePageUrl = scholarship.homePageUrl,
                startDate = scholarship.startDate,
                endDate = scholarship.endDate,
                viewCount = scholarship.viewCount,
                effortLevel = scholarship.effortLevel,
                effortLabel = scholarship.effortLabel,
                applyingStatus = applyingStatus,
                isStored = isStored
            )
        }
    }
}
