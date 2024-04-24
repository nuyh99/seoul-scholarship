package org.seoul.morlatjanghak.scholarship.dto

import org.springframework.data.domain.Sort

data class SearchOption(
    val keyword: String?,
    val viewCount: Boolean = false,
    val deadline: Boolean = false,
) {
    fun getSort(): Sort {
        val sort = Sort.unsorted()
        viewCount.let {
            sort.and(Sort.by(Sort.Order.desc("viewCount")))
        }
        deadline.let {
            sort.and(Sort.by(Sort.Order.asc("endDate")))
        }

        return sort
    }
}
