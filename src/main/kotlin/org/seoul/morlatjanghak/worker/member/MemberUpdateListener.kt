package org.seoul.morlatjanghak.worker.member

import org.seoul.morlatjanghak.member.event.MemberDeleteEvent
import org.seoul.morlatjanghak.member.event.MemberUpdateEvent
import org.seoul.morlatjanghak.recommendedscholarship.RecommendedScholarship
import org.seoul.morlatjanghak.recommendedscholarship.RecommendedScholarshipRepository
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.util.Random

@Component
class MemberUpdateListener(
    private val recommendedScholarshipRepository: RecommendedScholarshipRepository,
) {

    private val random: Random = SecureRandom()

    @Async
    @EventListener(MemberDeleteEvent::class)
    fun handle(event: MemberDeleteEvent) {
        recommendedScholarshipRepository.deleteAllByMemberId(event.memberId)
        log.info("Member deleted: ${event.memberId}")
    }

    @Async
    @EventListener(MemberUpdateEvent::class)
    fun handle(event: MemberUpdateEvent) {
        generateSequence { random.nextLong(1800) }
            .take(100)
            .map { RecommendedScholarship(memberId = event.memberId, scholarshipId = it) }
            .toList()
            .let { recommendedScholarshipRepository.saveAll(it) }
        log.info("Member updated and set new recommended scholarships!: ${event.memberId}")
    }

    companion object {
        private val log = LoggerFactory.getLogger(MemberUpdateListener::class.java)
    }
}
