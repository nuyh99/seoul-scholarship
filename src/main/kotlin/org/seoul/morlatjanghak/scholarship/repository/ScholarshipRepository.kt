package org.seoul.morlatjanghak.scholarship.repository

import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface ScholarshipRepository : JpaRepository<Scholarship, Long>, JpaSpecificationExecutor<Scholarship> {

    @Query(
        """
        SELECT s
        FROM Scholarship s
        WHERE s.id IN :ids
        """
    )
    fun findAllByIdInOrderBy(ids: List<Long>, pageable: Pageable): Page<Scholarship>
}