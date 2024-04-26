package org.seoul.morlatjanghak.recommendedscholarship

import org.springframework.data.jpa.repository.JpaRepository

interface RecommendedScholarshipRepository : JpaRepository<RecommendedScholarship, Long> {

    fun findAllByMemberId(memberId: String): List<RecommendedScholarship>

    fun deleteAllByMemberId(memberId: String)
}