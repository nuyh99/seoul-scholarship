package org.seoul.morlatjanghak.recommendedscholarship

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    indexes = [
        Index(name = "idx_recommended_scholarship_1", columnList = "member_id, scholarship_id"),
    ]
)
class RecommendedScholarship(

    @Id
    val id: Long? = null,
    @Column(columnDefinition = "varchar varchar(255) COMMENT '회원 ID'")
    val memberId: String,
    @Column(columnDefinition = "bigint COMMENT '장학금 ID'")
    val scholarshipId: Long,
)
