package org.seoul.morlatjanghak.worker.member

import org.seoul.morlatjanghak.appliedscholarship.AppliedScholarshipRepository
import org.seoul.morlatjanghak.member.MemberRepository
import org.seoul.morlatjanghak.member.event.MemberDeleteEvent
import org.seoul.morlatjanghak.member.event.MemberUpdateEvent
import org.seoul.morlatjanghak.recommendedscholarship.RecommendedScholarship
import org.seoul.morlatjanghak.recommendedscholarship.RecommendedScholarshipRepository
import org.seoul.morlatjanghak.recommendedscholarship.RecommendedScholarshipService
import org.seoul.morlatjanghak.storedscholarship.StoredScholarshipRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class MemberUpdateListener(
    private val recommendedScholarshipService: RecommendedScholarshipService,

    private val recommendedScholarshipRepository: RecommendedScholarshipRepository,
    private val memberRepository: MemberRepository,
    private val appliedScholarshipRepository: AppliedScholarshipRepository,
    private val storedScholarshipRepository: StoredScholarshipRepository,
) {

    @Async
    @Transactional
    @TransactionalEventListener(MemberDeleteEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: MemberDeleteEvent) {
        recommendedScholarshipRepository.deleteAllByMemberId(event.memberId)
        appliedScholarshipRepository.deleteAllByMemberId(event.memberId)
        storedScholarshipRepository.deleteAllByMemberId(event.memberId)
        log.info("Member deleted: ${event.memberId}")
    }

    @Async
    @Transactional
    @TransactionalEventListener(MemberUpdateEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: MemberUpdateEvent) {
        val member = memberRepository.findById(event.memberId).orElseThrow()

        val recommendedScholarships = recommendedScholarshipService.recommendAndReturnScholarshipIds(event.memberId)
            .map { RecommendedScholarship(memberId = event.memberId, scholarshipId = it) }
            .toList()

        recommendedScholarshipRepository.saveAll(recommendedScholarships)
        member.done(recommendedScholarships.size)

        log.info("Member updated and set new recommended scholarships!: ${event.memberId}")
    }

    companion object {
        private val log = LoggerFactory.getLogger(MemberUpdateListener::class.java)
    }
}
