package org.seoul.morlatjanghak.storedscholarship

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StoredScholarshipController(
    private val storedScholarshipService: StoredScholarshipService
) {

    @PostMapping("/api/scholarships/{scholarshipId}/members/{memberId}/stored")
    fun save(
        @PathVariable("scholarshipId") scholarshipId: Long,
        @PathVariable("memberId") memberId: String
    ): ResponseEntity<Unit> {
        storedScholarshipService.save(memberId, scholarshipId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/api/scholarships/{scholarshipId}/members/{memberId}/stored")
    fun delete(
        @PathVariable("scholarshipId") scholarshipId: Long,
        @PathVariable("memberId") memberId: String
    ): ResponseEntity<Unit> {
        storedScholarshipService.delete(memberId, scholarshipId)
        return ResponseEntity.ok().build()
    }
}
