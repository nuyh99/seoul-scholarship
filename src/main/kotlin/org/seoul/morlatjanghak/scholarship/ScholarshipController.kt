package org.seoul.morlatjanghak.scholarship

import org.seoul.morlatjanghak.scholarship.dto.ScholarshipDetailResponse
import org.seoul.morlatjanghak.scholarship.dto.ScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.SearchOption
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ScholarshipController(
    private val scholarshipService: ScholarshipService,
) {

    @GetMapping("/api/scholarships")
    fun findAll(pageable: Pageable, searchOption: SearchOption): Page<ScholarshipResponse> {
        return scholarshipService.findAll(pageable, searchOption)
    }

    @GetMapping("/api/scholarships/members/{memberId}")
    fun findRecommended(
        pageable: Pageable,
        searchOption: SearchOption,
        @PathVariable("memberId") memberId: String
    ): Page<ScholarshipResponse> {
        return scholarshipService.findRecommended(pageable, searchOption, memberId)
    }

    @GetMapping("/api/scholarships/{scholarshipId}")
    fun findById(@PathVariable("scholarshipId") scholarshipId: Long): ScholarshipDetailResponse {
        return scholarshipService.findByIdAndIncreaseViewCount(scholarshipId)
    }
}
