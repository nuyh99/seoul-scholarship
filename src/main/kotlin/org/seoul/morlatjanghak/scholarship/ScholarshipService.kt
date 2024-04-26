package org.seoul.morlatjanghak.scholarship

import org.seoul.morlatjanghak.recommendedscholarship.RecommendedScholarshipService
import org.seoul.morlatjanghak.scholarship.dto.ScholarshipDetailResponse
import org.seoul.morlatjanghak.scholarship.dto.ScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.SearchOption
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ScholarshipService(
    private val recommendedScholarshipService: RecommendedScholarshipService,
    private val scholarshipRepository: ScholarshipRepository
) {
    fun findAll(pageable: Pageable, searchOption: SearchOption): Page<ScholarshipResponse> {
        val sort = searchOption.getSort()
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, sort)
        val specification = ScholarshipSpecification(searchOption)

        return scholarshipRepository.findAll(specification, pageRequest)
            .map(ScholarshipResponse.Companion::from)
    }

    fun findRecommended(pageable: Pageable, searchOption: SearchOption, memberId: String): Page<ScholarshipResponse> {
        log.info("추천 검색 memberId: {}", memberId)

        val recommendedScholarshipIds = recommendedScholarshipService.findRecommendedScholarshipIds(memberId)
        return scholarshipRepository.findAllByIdInOrderBy(recommendedScholarshipIds, pageable)
            .map(ScholarshipResponse.Companion::from)
    }

    @Transactional
    fun findByIdAndIncreaseViewCount(id: Long): ScholarshipDetailResponse {
        val scholarship = scholarshipRepository.findById(id).orElseThrow()
        scholarship.increaseViewCount()

        return ScholarshipDetailResponse.from(scholarship)
    }

    companion object {
        private val log = LoggerFactory.getLogger(ScholarshipService::class.java)
    }
}
