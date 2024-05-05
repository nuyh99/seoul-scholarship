package org.seoul.morlatjanghak.member.dto

import org.seoul.morlatjanghak.member.Member

data class MemberCompletedResponse(
    val done: Boolean,
    val count: Int
) {
    companion object {
        fun from(member: Member): MemberCompletedResponse {
            return MemberCompletedResponse(member.isDone, member.totalRecommendedScholarshipCount)
        }
    }
}
