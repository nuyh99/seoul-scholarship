package org.seoul.morlatjanghak.member

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Member(

    @Id
    val id: String,
    val name: String?,
    val sex: String?,
    val birth: LocalDate?,
    val schoolName: String?,
    val majorCategory: String?,
    val semester: String?,
    val enrolled: String?,
    val lastSemesterGrade: Double?,
    val totalGrade: Double?,
    val incomeRange: String?,
    val militaryService: String?,
    val siblingExists: Boolean?,
    @CollectionTable(name = "member_detailed_condition")
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "detailed_condition")
    val detailedConditions: List<String>,

    var isDone: Boolean = false,
    var totalRecommendedScholarshipCount: Int = 0
) {
    fun done(totalRecommendedScholarshipCount: Int) {
        this.isDone = true
        this.totalRecommendedScholarshipCount = totalRecommendedScholarshipCount
    }
}
