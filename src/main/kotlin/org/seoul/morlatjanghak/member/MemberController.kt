package org.seoul.morlatjanghak.member

import org.seoul.morlatjanghak.member.dto.MemberCompletedResponse
import org.seoul.morlatjanghak.member.dto.MemberCreateRequest
import org.seoul.morlatjanghak.member.dto.MemberStatusResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/api/members")
    fun update(@RequestBody request: MemberCreateRequest): ResponseEntity<Void> {
        memberService.save(request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/api/members/{memberId}")
    fun delete(@PathVariable("memberId") memberId: String): ResponseEntity<Void> {
        memberService.delete(memberId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/api/members/{memberId}/completed")
    fun isCompleted(@PathVariable("memberId") memberId: String): ResponseEntity<MemberCompletedResponse> {
        val response = memberService.isUpdateDone(memberId)
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/api/members/migrate")
    fun migrate(): ResponseEntity<Void> {
        memberService.updateAll()
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/api/members/{memberId}/status")
    fun getStatus(@PathVariable("memberId") memberId: String): ResponseEntity<MemberStatusResponse> {
        val response = memberService.getStatus(memberId)
        return ResponseEntity.ok().body(response)
    }
}
