package org.seoul.morlatjanghak.member.dto

data class MemberStatusResponse(
    val name: String,
    val totalSupportedAmount: Long,
    val appliedScholarshipCount: Long,
    val passedScholarshipCount: Long,
)
