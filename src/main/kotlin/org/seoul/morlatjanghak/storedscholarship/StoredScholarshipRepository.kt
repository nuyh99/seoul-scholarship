package org.seoul.morlatjanghak.storedscholarship

import org.springframework.data.jpa.repository.JpaRepository

interface StoredScholarshipRepository : JpaRepository<StoredScholarship, Long> {

    fun existsByMemberIdAndScholarshipId(memberId: String, scholarshipId: Long): Boolean

    fun deleteByMemberIdAndScholarshipId(memberId: String, scholarshipId: Long)

    fun deleteAllByMemberId(memberId: String)

    fun findAllByMemberId(memberId: String): List<StoredScholarship>
}