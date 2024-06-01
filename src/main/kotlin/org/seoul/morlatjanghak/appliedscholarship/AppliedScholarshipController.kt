package org.seoul.morlatjanghak.appliedscholarship

import org.seoul.morlatjanghak.appliedscholarship.dto.AppliedScholarshipRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
        @RequestBody(required = false) request: AppliedScholarshipRequest
    ): ResponseEntity<Unit> {
        appliedScholarshipService.update(memberId, scholarshipId, status, request)
        return ResponseEntity.ok().build()
    }
}
