package org.seoul.morlatjanghak.admin

import org.seoul.morlatjanghak.admin.domain.UploadStatus
import org.seoul.morlatjanghak.admin.dto.UploadResponse
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime

@Controller
class AdminController {

    @GetMapping("/admin/upload")
    fun index(model: Model, @PageableDefault pageable: Pageable): String {
        val mockResponse = UploadResponse(
            number = 1,
            fileName = "테스트 파일.csv",
            requestDate = LocalDateTime.now(),
            completed = UploadStatus.SUCCESS
        )
        val response2 = UploadResponse(
            number = 2,
            fileName = "테스트 파일2.csv",
            requestDate = LocalDateTime.now(),
            completed = UploadStatus.IN_PROGRESS
        )
        val response3 = UploadResponse(
            number = 3,
            fileName = "테스트 파일3.csv",
            requestDate = LocalDateTime.now(),
            completed = UploadStatus.FAIL
        )

        PageImpl(listOf(mockResponse, response2, response3), pageable, 1)
            .let { model.addAttribute("itemPage", it) }

        return "upload"
    }
}