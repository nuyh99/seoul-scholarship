package org.seoul.morlatjanghak.scholarship

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ScholarshipRepository : JpaRepository<Scholarship, Long>, JpaSpecificationExecutor<Scholarship> {

    fun findAllByIdIn(ids: List<Long>, pageable: Pageable): Page<Scholarship>
}