package org.seoul.morlatjanghak.scholarship.dto

data class AppliedSearchOption(
    val deadline: Boolean = false,
    val recent: Boolean = false
) {
    init {
        if (deadline && recent) {
            throw IllegalArgumentException("deadline and recent cannot be true at the same time")
        }
    }

    fun sort(scholarships: List<AppliedScholarshipResponse>): List<AppliedScholarshipResponse> {
        if (deadline) {
            return scholarships.sortedBy { it.endDate }
        }
        if (recent) {
            return scholarships.sortedByDescending { it.modifiedDate }
        }

        return scholarships.sortedByDescending { it.startDate }
    }
}
