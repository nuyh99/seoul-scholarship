package org.seoul.morlatjanghak.scholarship.dto

data class StoredSearchOption(
    val deadline: Boolean = false,
    val recent: Boolean = false
) {
    init {
        if (deadline && recent) {
            throw IllegalArgumentException("deadline and recent cannot be true at the same time")
        }
    }

    fun sort(scholarships: List<StoredScholarshipResponse>): List<StoredScholarshipResponse> {
        if (deadline) {
            return scholarships.sortedBy { it.endDate }
        }
        if (recent) {
            return scholarships.sortedByDescending { it.storedDate }
        }

        return scholarships.sortedByDescending { it.startDate }
    }
}
