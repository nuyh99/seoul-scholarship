package org.seoul.morlatjanghak.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, String> {
}