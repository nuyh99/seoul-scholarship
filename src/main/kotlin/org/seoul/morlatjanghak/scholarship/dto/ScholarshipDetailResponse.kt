package org.seoul.morlatjanghak.scholarship.dto

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
) {
    companion object {
        fun from(scholarship: Scholarship): ScholarshipDetailResponse {
            return ScholarshipDetailResponse(
                scholarship.id,
                scholarship.organization,
                scholarship.productName,
                scholarship.organizationType,
                scholarship.scholarshipType,
                scholarship.universityCategory,
                scholarship.grade,
                scholarship.majorCategory,
                scholarship.gradeDetails,
                scholarship.incomeDetails,
                scholarship.supportDetails,
                scholarship.specificQualificationDetails,
                scholarship.localResidencyDetails,
                scholarship.selectionMethodDetails,
                scholarship.selectionCountDetails,
                scholarship.eligibilityRestrictionDetails,
                scholarship.recommendationRequiredDetails,
                scholarship.requiredDocumentDetails,
                scholarship.homePageUrl,
                scholarship.startDate,
                scholarship.endDate,
                scholarship.viewCount,
            )
        }
    }
}
