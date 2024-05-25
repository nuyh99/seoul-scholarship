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

        PageImpl(listOf(mockResponse), pageable, 1)
            .let { model.addAttribute("itemPage", it) }

        return "upload"
    }
}