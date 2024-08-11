package org.seoul.morlatjanghak.worker.member

import org.seoul.morlatjanghak.admin.UploadFileHistoryRepository
import org.seoul.morlatjanghak.admin.domain.ScholarshipUpdateEvent
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
import org.springframework.transaction.annotation.Propagation
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
    private val uploadFileHistoryRepository: UploadFileHistoryRepository,
) {

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(MemberDeleteEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: MemberDeleteEvent) {
        recommendedScholarshipRepository.deleteAllByMemberId(event.memberId)
        appliedScholarshipRepository.deleteAllByMemberId(event.memberId)
        storedScholarshipRepository.deleteAllByMemberId(event.memberId)
        log.info("Member deleted: ${event.memberId}")
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(MemberUpdateEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: MemberUpdateEvent) {
        val member = memberRepository.findById(event.memberId).orElseThrow()

        val recommendedScholarships = recommendedScholarshipService.recommendAndReturnScholarshipIds(event.memberId)
            .map { RecommendedScholarship(memberId = event.memberId, scholarshipId = it) }
            .toList()

        recommendedScholarshipRepository.deleteAllByMemberId(event.memberId)
        recommendedScholarshipRepository.saveAll(recommendedScholarships)
        member.done(recommendedScholarships.size)

        log.info("Member updated and set new recommended scholarships!: ${event.memberId}")
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(ScholarshipUpdateEvent::class, phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: ScholarshipUpdateEvent) {
        val members = memberRepository.findAll()

        try {
            members.forEach { member ->
                val recommendedScholarships =
                    recommendedScholarshipService.recommendAndReturnScholarshipIds(memberId = member.id)
                        .map { RecommendedScholarship(memberId = member.id, scholarshipId = it) }
                        .toList()

                recommendedScholarshipRepository.deleteAllByMemberId(member.id)
                recommendedScholarshipRepository.saveAll(recommendedScholarships)
                member.done(recommendedScholarships.size)
            }
            log.info("장학금 업데이트 완료. 파일 업로드 번호: ${event.historyId}")
            uploadFileHistoryRepository.findById(event.historyId).ifPresent { it.complete() }
        } catch (e: Exception) {
            markAsFailed(event.historyId)
            log.error("Error occurred while updating scholarships for all members: ${e.message}")
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun markAsFailed(historyId: Long) {
        uploadFileHistoryRepository.findById(historyId).ifPresent { it.fail() }
    }

    companion object {
        private val log = LoggerFactory.getLogger(MemberUpdateListener::class.java)
    }
}
