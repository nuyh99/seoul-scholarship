package org.seoul.morlatjanghak.member.dto

import org.seoul.morlatjanghak.member.Member
import java.time.LocalDate

data class MemberCreateRequest(
    val id: String,
    val name: String,
    val sex: String,
    val birth: LocalDate,
    val schoolName: String,
    val majorCategory: String,
    val semester: String,
    val enrolled: String,
    val lastSemesterGrade: Double,
    val totalGrade: Double,
    val incomeRange: String,
    val militaryService: String,
    val siblingExists: Boolean,
    val detailedConditions: List<String>
) {
    fun toEntity(): Member {
        return Member(
            id = id,
            name = name,
            sex = sex,
            birth = birth,
            schoolName = schoolName,
            majorCategory = majorCategory,
            semester = semester,
            enrolled = enrolled,
            lastSemesterGrade = lastSemesterGrade,
            totalGrade = totalGrade,
            incomeRange = incomeRange,
            militaryService = militaryService,
            siblingExists = siblingExists,
            detailedConditions = detailedConditions
        )
    }
}
