package org.seoul.morlatjanghak.scholarship.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate


@Entity
class Scholarship(
    @Id
    val id: Long,
    @Column(columnDefinition = "varchar(255) COMMENT '운영기관명'")
    val organization: String,
    @Column(columnDefinition = "varchar(255) COMMENT '상품명'")
    val productName: String,
    @Column(columnDefinition = "varchar(255) COMMENT '운영기관구분'")
    val organizationType: String,
    @Column(columnDefinition = "varchar(255) COMMENT '학자금유형구분'")
    val scholarshipType: String,
    @Column(columnDefinition = "varchar(255) COMMENT '대학구분'")
    val universityCategory: String,
    @Column(columnDefinition = "varchar(255) COMMENT '학년구분'")
    val grade: String,
    @Column(columnDefinition = "varchar(255) COMMENT '학과구분'")
    val majorCategory: String,
    @Column(columnDefinition = "TEXT COMMENT '성적기준'")
    val gradeDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '소득기준'")
    val incomeDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '지원내역'")
    val supportDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '특정자격'")
    val specificQualificationDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '지역거주여부'")
    val localResidencyDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '선발방법'")
    val selectionMethodDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '선발인원'")
    val selectionCountDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '자격제한'")
    val eligibilityRestrictionDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '추천필요여부'")
    val recommendationRequiredDetails: String,
    @Column(columnDefinition = "TEXT COMMENT '제출서류'")
    val requiredDocumentDetails: String,
    @Column(columnDefinition = "varchar(255) COMMENT '홈페이지 주소'")
    val homePageUrl: String,
    @Column(columnDefinition = "date COMMENT '모집시작일'")
    @DateTimeFormat(pattern = "yyyy-M-d")
    val startDate: LocalDate?,
    @Column(columnDefinition = "date COMMENT '모집종료일'")
    @DateTimeFormat(pattern = "yyyy-M-d")
    val endDate: LocalDate?,
    @Column(columnDefinition = "bigint COMMENT '조회수'")
    var viewCount: Long = 0,
    @Column(columnDefinition = "float COMMENT '정제된 직전 학기 성적 기준'")
    val formattedLastSemesterGrade: Double,
    @Column(columnDefinition = "float COMMENT '정제된 전체 학기 성적 기준'")
    val formattedTotalSemesterGrade: Double,
    @Column(columnDefinition = "int COMMENT '정제된 소득 구간 기준'")
    val formattedIncome: Int,
    @Column(columnDefinition = "varchar(100) COMMENT '정제된 지원 내역'")
    val formattedSupportDetails: String,
    @Column(columnDefinition = "bigint COMMENT '노력지수 레벨'")
    val effortLevel: Int,
    @Column(columnDefinition = "varchar(10) COMMENT '노력지수 레이블 상 중 하'")
    val effortLabel: String,
) {
    fun increaseViewCount() {
        viewCount++
    }
}
