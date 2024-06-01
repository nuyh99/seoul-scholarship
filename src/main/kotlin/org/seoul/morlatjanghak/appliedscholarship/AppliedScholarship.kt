package org.seoul.morlatjanghak.appliedscholarship

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.seoul.morlatjanghak.common.BaseTimeEntity

@Entity
class AppliedScholarship(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var memberId: String,
    var scholarshipId: Long,
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50) COMMENT '지원 상태'")
    var status: ApplyingStatus = ApplyingStatus.NOTHING,

    @Column(columnDefinition = "int(11) DEFAULT 0 COMMENT '지원 받은 금액'")
    var supportedAmount: Long = 0
) : BaseTimeEntity() {

    fun updateStatus(status: ApplyingStatus, supportedAmount: Long) {
        this.status = status
        this.supportedAmount = supportedAmount
    }
}
