package org.seoul.morlatjanghak.member

import org.seoul.morlatjanghak.member.dto.MemberCompletedResponse
import org.seoul.morlatjanghak.member.dto.MemberCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
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
}
