package org.seoul.morlatjanghak.appliedscholarship

import org.seoul.morlatjanghak.appliedscholarship.dto.AppliedScholarshipRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AppliedScholarshipService(
    private val appliedScholarshipRepository: AppliedScholarshipRepository,
) {

    @Transactional
    fun update(
        memberId: String,
        scholarshipId: Long,
        applyingStatus: ApplyingStatus,
        request: AppliedScholarshipRequest
    ) {
        val appliedScholarship = (appliedScholarshipRepository.findByScholarshipIdAndMemberId(scholarshipId, memberId)
            ?: AppliedScholarship(memberId = memberId, scholarshipId = scholarshipId))

        appliedScholarship.updateStatus(applyingStatus, request.supportedAmount)
        appliedScholarshipRepository.save(appliedScholarship)

        if (appliedScholarship.status == ApplyingStatus.NOTHING) {
            appliedScholarshipRepository.delete(appliedScholarship)
        }
    }
}
