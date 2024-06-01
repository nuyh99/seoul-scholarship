package org.seoul.morlatjanghak.member

import org.seoul.morlatjanghak.appliedscholarship.AppliedScholarshipRepository
import org.seoul.morlatjanghak.appliedscholarship.ApplyingStatus
import org.seoul.morlatjanghak.member.dto.MemberCompletedResponse
import org.seoul.morlatjanghak.member.dto.MemberCreateRequest
import org.seoul.morlatjanghak.member.dto.MemberStatusResponse
import org.seoul.morlatjanghak.member.event.MemberDeleteEvent
import org.seoul.morlatjanghak.member.event.MemberUpdateEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
    private val appliedRepository: AppliedScholarshipRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {

    @Transactional
    fun save(request: MemberCreateRequest): Member {
        val saved = memberRepository.save(request.toEntity())
        applicationEventPublisher.publishEvent(MemberUpdateEvent(saved.id))

        return saved
    }

    @Transactional
    fun delete(memberId: String) {
        memberRepository.deleteById(memberId)
        applicationEventPublisher.publishEvent(MemberDeleteEvent(memberId))
    }

    fun isUpdateDone(memberId: String): MemberCompletedResponse {
        val member = memberRepository.findById(memberId)
            .orElseThrow { throw IllegalArgumentException("memberId: $memberId member not found") }
        return MemberCompletedResponse.from(member)
    }

    fun updateAll() {
        memberRepository.findAll().forEach {
            applicationEventPublisher.publishEvent(MemberUpdateEvent(it.id))
        }
    }

    fun getStatus(memberId: String): MemberStatusResponse {
        val member = memberRepository.findById(memberId)
            .orElseThrow { throw IllegalArgumentException("memberId: $memberId member not found") }
        val applies = appliedRepository.findAllByMemberId(memberId)

        val totalSupportedAmount = applies.sumOf { it.supportedAmount }
        val appliedScholarshipCount = applies.size.toLong()
        val passedScholarshipCount = applies.count { it.status == ApplyingStatus.PASSED }.toLong()

        return MemberStatusResponse(
            name = member.name ?: "알 수 없는 이름",
            totalSupportedAmount = totalSupportedAmount,
            appliedScholarshipCount = appliedScholarshipCount,
            passedScholarshipCount = passedScholarshipCount,
        )

    }
}
