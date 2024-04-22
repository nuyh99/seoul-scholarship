package org.seoul.morlatjanghak.member

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Member(

    @Id
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
    @ElementCollection(fetch = FetchType.EAGER)
    val significant: List<String>
)
