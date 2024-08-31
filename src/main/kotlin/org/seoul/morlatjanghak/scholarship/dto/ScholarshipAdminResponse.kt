package org.seoul.morlatjanghak.scholarship.dto

import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import java.time.LocalDate

data class ScholarshipAdminResponse(
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
    val formattedLastSemesterGrade: Double,
    val formattedTotalSemesterGrade: Double,
    val formattedIncome: Int,
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
) {
    companion object {
        fun from(entity: Scholarship): ScholarshipAdminResponse {
            return ScholarshipAdminResponse(
                id = entity.id,
                organization = entity.organization,
                productName = entity.productName,
                organizationType = entity.organizationType,
                scholarshipType = entity.scholarshipType,
                universityCategory = entity.universityCategory,
                grade = entity.grade,
                majorCategory = entity.majorCategory,
                gradeDetails = entity.gradeDetails,
                incomeDetails = entity.incomeDetails,
                supportDetails = entity.supportDetails,
                formattedSupportDetails = entity.formattedSupportDetails,
                specificQualificationDetails = entity.specificQualificationDetails,
                localResidencyDetails = entity.localResidencyDetails,
                selectionMethodDetails = entity.selectionMethodDetails,
                selectionCountDetails = entity.selectionCountDetails,
                eligibilityRestrictionDetails = entity.eligibilityRestrictionDetails,
                recommendationRequiredDetails = entity.recommendationRequiredDetails,
                requiredDocumentDetails = entity.requiredDocumentDetails,
                homePageUrl = entity.homePageUrl,
                startDate = entity.startDate,
                endDate = entity.endDate,
                viewCount = entity.viewCount,
                effortLevel = entity.effortLevel,
                effortLabel = entity.effortLabel,
                formattedLastSemesterGrade = entity.formattedLastSemesterGrade,
                formattedTotalSemesterGrade = entity.formattedTotalSemesterGrade,
                formattedIncome = entity.formattedIncome,
            )
        }
    }
}
