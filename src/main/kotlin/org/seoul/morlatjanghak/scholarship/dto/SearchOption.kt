package org.seoul.morlatjanghak.scholarship.dto

import org.springframework.data.domain.Sort

data class SearchOption(
    val keyword: String?,
    val viewCount: Boolean = false,
) {
    fun getSort(): Sort {
        val sort = Sort.unsorted()
        if (viewCount) {
            sort.and(Sort.by(Sort.Order.desc("viewCount")))
        }

        return sort
    }
}
