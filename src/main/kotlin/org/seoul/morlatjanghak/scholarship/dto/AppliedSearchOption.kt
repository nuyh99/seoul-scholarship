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

    fun sort(scholarships: List<AppliedScholarshipResponse>) {
        if (deadline) {
            scholarships.sortedBy { it.endDate }
            return
        }
        if (recent) {
            scholarships.sortedByDescending { it.modifiedDate }
            return
        }

        scholarships.sortedByDescending { it.startDate }
    }
}
