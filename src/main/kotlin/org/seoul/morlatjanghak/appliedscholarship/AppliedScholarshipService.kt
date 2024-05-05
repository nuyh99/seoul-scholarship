package org.seoul.morlatjanghak.appliedscholarship

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AppliedScholarshipService(
    private val appliedScholarshipRepository: AppliedScholarshipRepository,
) {

    @Transactional
    fun update(memberId: String, scholarshipId: Long, applyingStatus: ApplyingStatus) {
        val appliedScholarship = (appliedScholarshipRepository.findByScholarshipIdAndMemberId(scholarshipId, memberId)
            ?: AppliedScholarship(memberId = memberId, scholarshipId = scholarshipId))

        appliedScholarship.updateStatus(applyingStatus)
        appliedScholarshipRepository.save(appliedScholarship)
    }
}
