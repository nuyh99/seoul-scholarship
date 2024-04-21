package org.seoul.morlatjanghak.member

import jakarta.persistence.Entity
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
)
