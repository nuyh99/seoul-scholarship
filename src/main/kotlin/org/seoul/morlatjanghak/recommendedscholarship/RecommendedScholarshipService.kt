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
        val incomeFiltered = scholarshipRepository.findAllIdsByIncomeEqualOrGreaterThan(member.incomeRange ?: 0)
            .toSet()
        val lastSemesterFiltered =
            scholarshipRepository.findAllIdsByLastSemesterGradeEqualsOrGreaterThan(member.formattedLastSemesterGrade())
                .toSet()
        val totalSemesterFiltered =
            scholarshipRepository.findAllIdsByTotalSemesterGradeEqualsOrGreaterThan(member.formattedTotalSemesterGrade())
                .toSet()

        //현재 학기, 전공, 소득구간, 직전학기 성적, 전체학기 성적 기준 필터링
        return semesterFiltered.intersect(majorFiltered)
            .intersect(incomeFiltered)
            .intersect(lastSemesterFiltered)
            .intersect(totalSemesterFiltered)
    }
}
