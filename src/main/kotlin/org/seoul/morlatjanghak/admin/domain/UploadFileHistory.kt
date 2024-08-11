package org.seoul.morlatjanghak.admin.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@EntityListeners(value = [AuditingEntityListener::class])
class UploadFileHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(columnDefinition = "varchar(255) not null comment '파일명'")
    var fileName: String,

    @CreatedDate
    @Column(columnDefinition = "datetime(6) not null comment '생성일'")
    var createdDate: String? = null,

    @Column(columnDefinition = "varchar(50) not null comment '처리 상태'")
    @Enumerated(EnumType.STRING)
    var status: UploadStatus = UploadStatus.IN_PROGRESS
) {
    fun complete() {
        status = UploadStatus.SUCCESS
    }

    fun fail() {
        status = UploadStatus.FAIL
    }
}
