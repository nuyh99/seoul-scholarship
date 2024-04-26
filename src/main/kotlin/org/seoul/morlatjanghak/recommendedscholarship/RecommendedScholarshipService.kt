package org.seoul.morlatjanghak.recommendedscholarship

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class RecommendedScholarshipService(
    private val recommendedScholarshipRepository: RecommendedScholarshipRepository,
) {

    fun findRecommendedScholarshipIds(memberId: String): List<Long> {
        return recommendedScholarshipRepository.findAllByMemberId(memberId)
            .map { it.scholarshipId }
    }
}