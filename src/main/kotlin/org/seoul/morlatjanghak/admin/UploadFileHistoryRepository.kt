package org.seoul.morlatjanghak.admin

import org.seoul.morlatjanghak.admin.domain.UploadFileHistory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UploadFileHistoryRepository : JpaRepository<UploadFileHistory, Long> {

    fun findAllByOrderByCreatedDateDesc(pageable: Pageable): Page<UploadFileHistory>
}