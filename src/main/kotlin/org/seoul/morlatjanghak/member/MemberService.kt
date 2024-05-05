package org.seoul.morlatjanghak.member

import org.seoul.morlatjanghak.member.dto.MemberCompletedResponse
import org.seoul.morlatjanghak.member.dto.MemberCreateRequest
import org.seoul.morlatjanghak.member.event.MemberDeleteEvent
import org.seoul.morlatjanghak.member.event.MemberUpdateEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
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
        val member = memberRepository.findById(memberId).orElseThrow()
        return MemberCompletedResponse.from(member)
    }
}