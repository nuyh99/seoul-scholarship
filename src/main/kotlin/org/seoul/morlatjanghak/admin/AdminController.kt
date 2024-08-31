package org.seoul.morlatjanghak.admin

import org.seoul.morlatjanghak.scholarship.ScholarshipService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile


@Controller
class AdminController(
    private val uploadFileHistoryService: UploadFileHistoryService,
    private val scholarshipService: ScholarshipService,
) {

    @GetMapping("/admin/upload")
    fun upload(model: Model, @PageableDefault pageable: Pageable): String {
        val histories = uploadFileHistoryService.getHistories(pageable)
        model.addAttribute("itemPage", histories)
        return "upload"
    }

    @PostMapping("/admin/upload")
    fun handleFileUpload(@RequestParam("file") file: MultipartFile): String {
        uploadFileHistoryService.upload(file)
        return "redirect:/admin/upload"
    }

    @GetMapping("/admin/list")
    fun list(model: Model, @PageableDefault pageable: Pageable): String {
        val scholarships = scholarshipService.findScholarships(pageable)
        model.addAttribute("itemPage", scholarships)
        return "list"
    }
}