package org.seoul.morlatjanghak.scholarship

import org.seoul.morlatjanghak.scholarship.dto.AppliedScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.AppliedSearchOption
import org.seoul.morlatjanghak.scholarship.dto.ScholarshipDetailResponse
import org.seoul.morlatjanghak.scholarship.dto.ScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.SearchOption
import org.seoul.morlatjanghak.scholarship.dto.StoredScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.StoredSearchOption
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
    fun findAll(memberId: String, pageable: Pageable, searchOption: SearchOption): Page<ScholarshipResponse> {
        return scholarshipService.findAll(memberId, pageable, searchOption)
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
    fun findById(
        @PathVariable("scholarshipId") scholarshipId: Long,
        memberId: String = "unknown"
    ): ScholarshipDetailResponse {
        return scholarshipService.findByIdAndIncreaseViewCount(scholarshipId, memberId)
    }

    @GetMapping("/api/scholarships/members/{memberId}/stored")
    fun findStored(
        searchOption: StoredSearchOption,
        @PathVariable("memberId") memberId: String
    ): List<StoredScholarshipResponse> {
        return scholarshipService.findStored(searchOption, memberId)
    }

    @GetMapping("/api/scholarships/members/{memberId}/applied")
    fun findApplied(
        searchOption: AppliedSearchOption,
        @PathVariable("memberId") memberId: String
    ): List<AppliedScholarshipResponse> {
        return scholarshipService.findApplied(searchOption, memberId)
    }
}
