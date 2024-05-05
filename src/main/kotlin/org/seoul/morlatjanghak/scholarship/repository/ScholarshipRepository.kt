package org.seoul.morlatjanghak.scholarship.repository

import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ScholarshipRepository : JpaRepository<Scholarship, Long>, JpaSpecificationExecutor<Scholarship> {

    @Query(
        """
        SELECT s
        FROM Scholarship s
        WHERE s.id IN :ids
        """
    )
    fun findAllByIdInOrderBy(ids: List<Long>, pageable: Pageable): Page<Scholarship>

    @Query(
        """
        SELECT s.id
        FROM Scholarship s
        WHERE s.grade LIKE %:semester% OR s.grade LIKE '%제한없음%' OR s.grade LIKE '%해당없음%'
        """
    )
    fun findAllIdsBySemesterLike(@Param("semester") semester: String): List<Long>

    @Query(
        """
        SELECT s.id
        FROM Scholarship s
        WHERE s.majorCategory LIKE %:major% OR s.majorCategory LIKE '%제한없음%'
        """
    )
    fun findAllIdsByMajorLike(@Param("major") major: String): List<Long>
}