package org.seoul.morlatjanghak.admin

import org.seoul.morlatjanghak.admin.domain.ScholarshipUpdateEvent
import org.seoul.morlatjanghak.admin.domain.UploadFileHistory
import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import org.seoul.morlatjanghak.scholarship.repository.ScholarshipRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.time.LocalDate

@Service
class UploadFileHistoryService(
    private val uploadFileHistoryRepository: UploadFileHistoryRepository,
    private val scholarshipRepository: ScholarshipRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {

    @Transactional(readOnly = true)
    fun getHistories(pageable: Pageable): Page<UploadFileHistory> {
        return uploadFileHistoryRepository.findAllByOrderByCreatedDateDesc(pageable)
    }

    @Transactional
    fun upload(file: MultipartFile) {
        val history = uploadFileHistoryRepository.save(
            UploadFileHistory(fileName = file.originalFilename ?: "파일")
        )

        try {
            val scholarships = mutableListOf<Scholarship>()
            BufferedReader(file.inputStream.reader()).use { reader ->
                reader.readLine()
                reader.readLines().forEach { line ->
                    val values = line.split(",")
                    val scholarship = Scholarship(
                        id = values[0].toLong(),
                        eligibilityRestrictionDetails = values[1],
                        endDate = LocalDate.parse(values[2]),
                        grade = values[3],
                        gradeDetails = values[4],
                        homePageUrl = values[5],
                        incomeDetails = values[6],
                        localResidencyDetails = values[7],
                        majorCategory = values[8],
                        organization = values[9],
                        organizationType = values[10],
                        productName = values[11],
                        recommendationRequiredDetails = values[12],
                        requiredDocumentDetails = values[13],
                        scholarshipType = values[14],
                        selectionCountDetails = values[15],
                        selectionMethodDetails = values[16],
                        specificQualificationDetails = values[17],
                        startDate = LocalDate.parse(values[18]),
                        supportDetails = values[19],
                        universityCategory = values[20],
                        viewCount = values[21].toLong(),
                        formattedLastSemesterGrade = values[22].toDouble(),
                        formattedTotalSemesterGrade = values[23].toDouble(),
                        formattedIncome = values[24].toInt(),
                        formattedSupportDetails = values[25],
                        effortLevel = values[26].toInt(),
                        effortLabel = values[27]
                    )
                    scholarships.add(scholarship)
                }
            }
            scholarshipRepository.saveAll(scholarships)
            eventPublisher.publishEvent(ScholarshipUpdateEvent(history.id!!))
        } catch (e: Exception) {
            markAsFailed(history.id!!)
            log.error("Failed to upload file", e)
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun markAsFailed(historyId: Long) {
        uploadFileHistoryRepository.findById(historyId).ifPresent { it.fail() }
    }

    companion object {
        private val log = LoggerFactory.getLogger(UploadFileHistoryService::class.java)
    }
}