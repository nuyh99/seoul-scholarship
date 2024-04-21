package org.seoul.morlatjanghak.scholarship

import org.seoul.morlatjanghak.scholarship.dto.ScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.SearchOption
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.SecureRandom

@Transactional(readOnly = true)
@Service
class ScholarshipService(
    private val scholarshipRepository: ScholarshipRepository
) {
    private val log = LoggerFactory.getLogger(ScholarshipService::class.java)
    private val random = SecureRandom()

    fun findAll(pageable: Pageable, searchOption: SearchOption): Page<ScholarshipResponse> {
        val sort = searchOption.getSort()
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, sort)
        val specification = ScholarshipSpecification(searchOption)

        return scholarshipRepository.findAll(specification, pageRequest)
            .map(ScholarshipResponse.Companion::from)
    }

    fun findRecommended(pageable: Pageable, searchOption: SearchOption, memberId: String): Page<ScholarshipResponse> {
        log.info("추천 검색 memberId: {}", memberId)

        val sort = searchOption.getSort()
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, sort)

        return scholarshipRepository.findAllByIdIn(getRandomIndices(), pageRequest)
            .map(ScholarshipResponse.Companion::from)
    }

    fun getRandomIndices(): List<Long> {
        return generateSequence { random.nextLong(2000) }
            .take(100)
            .toList()
    }

    @Transactional
    fun findById(id: Long): ScholarshipResponse {
        val scholarship = scholarshipRepository.findById(id).orElseThrow()
        scholarship.increaseViewCount()

        return ScholarshipResponse.from(scholarship)
    }
}
