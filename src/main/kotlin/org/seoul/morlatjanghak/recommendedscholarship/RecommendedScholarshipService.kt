package org.seoul.morlatjanghak.recommendedscholarship

import org.seoul.morlatjanghak.member.MemberRepository
import org.seoul.morlatjanghak.scholarship.repository.ScholarshipRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class RecommendedScholarshipService(
    private val scholarshipRepository: ScholarshipRepository,
    private val memberRepository: MemberRepository
) {

    fun recommendAndReturnScholarshipIds(memberId: String): Set<Long> {
        val member = memberRepository.findById(memberId).orElseThrow()

        val semesterFiltered = scholarshipRepository.findAllIdsBySemesterLike(member.semester ?: "")
            .toSet()
        val majorFiltered = scholarshipRepository.findAllIdsByMajorLike(member.majorCategory ?: "")
            .toSet()

        return semesterFiltered.intersect(majorFiltered)
    }
}