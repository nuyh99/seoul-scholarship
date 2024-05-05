package org.seoul.morlatjanghak.storedscholarship

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StoredScholarshipService(
    private val storedScholarshipRepository: StoredScholarshipRepository
) {

    @Transactional
    fun save(memberId: String, scholarshipId: Long) {
        storedScholarshipRepository.existsByMemberIdAndScholarshipId(memberId, scholarshipId)
            .takeIf { it }
            ?.run { return }

        storedScholarshipRepository.save(StoredScholarship(memberId = memberId, scholarshipId = scholarshipId))
    }

    @Transactional
    fun delete(memberId: String, scholarshipId: Long) {
        storedScholarshipRepository.deleteByMemberIdAndScholarshipId(memberId, scholarshipId)
    }
}