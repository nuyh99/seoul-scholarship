package org.seoul.morlatjanghak.appliedscholarship

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppliedScholarshipController(
    private val appliedScholarshipService: AppliedScholarshipService,
) {

    @PostMapping("/api/scholarships/{scholarshipId}/members/{memberId}/{status}")
    fun applyScholarship(
        @PathVariable("scholarshipId") scholarshipId: Long,
        @PathVariable("memberId") memberId: String,
        @PathVariable("status") status: ApplyingStatus,
    ): ResponseEntity<Unit> {
        appliedScholarshipService.update(memberId, scholarshipId, status)
        return ResponseEntity.ok().build()
    }
}
