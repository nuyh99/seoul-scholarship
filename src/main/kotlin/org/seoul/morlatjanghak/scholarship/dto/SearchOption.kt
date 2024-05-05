package org.seoul.morlatjanghak.scholarship.dto

import org.springframework.data.domain.Sort

data class SearchOption(
    val keyword: String?,
    val viewCount: Boolean = false,
    val deadline: Boolean = false,
) {
    fun getSort(): Sort {
        var sort = Sort.by("startDate").descending()

        if (viewCount) {
            sort = Sort.by(Sort.Order.desc("viewCount"))
        }
        if (deadline) {
            sort = Sort.by(Sort.Order.asc("endDate"))
        }

        return sort
    }
}
