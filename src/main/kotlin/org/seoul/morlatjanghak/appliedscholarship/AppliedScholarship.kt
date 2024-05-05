package org.seoul.morlatjanghak.appliedscholarship

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
    var status: ApplyingStatus = ApplyingStatus.NOTHING,
) : BaseTimeEntity() {

    fun updateStatus(status: ApplyingStatus) {
        this.status = status
    }
}
