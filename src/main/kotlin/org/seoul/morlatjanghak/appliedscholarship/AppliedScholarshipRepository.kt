package org.seoul.morlatjanghak.appliedscholarship

import org.springframework.data.jpa.repository.JpaRepository

interface AppliedScholarshipRepository : JpaRepository<AppliedScholarship, Long> {

    fun findByScholarshipIdAndMemberId(scholarshipId: Long, memberId: String): AppliedScholarship?

    fun deleteAllByMemberId(memberId: String)

    fun findAllByMemberId(memberId: String): List<AppliedScholarship>
}