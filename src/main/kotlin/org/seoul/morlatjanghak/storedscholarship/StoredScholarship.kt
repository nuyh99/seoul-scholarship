package org.seoul.morlatjanghak.storedscholarship

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.seoul.morlatjanghak.common.BaseTimeEntity

@Entity
class StoredScholarship(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var memberId: String,
    var scholarshipId: Long,
) : BaseTimeEntity()