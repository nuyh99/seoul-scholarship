package org.seoul.morlatjanghak.member

import org.seoul.morlatjanghak.member.dto.MemberCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun save(request: MemberCreateRequest): Member {
        return memberRepository.save(request.toEntity())
    }

    @Transactional
    fun delete(memberId: String) {
        memberRepository.deleteById(memberId)
    }
}