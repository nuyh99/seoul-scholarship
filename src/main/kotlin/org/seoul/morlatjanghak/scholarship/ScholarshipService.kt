package org.seoul.morlatjanghak.scholarship

import org.seoul.morlatjanghak.appliedscholarship.AppliedScholarshipRepository
import org.seoul.morlatjanghak.appliedscholarship.ApplyingStatus
import org.seoul.morlatjanghak.recommendedscholarship.RecommendedScholarshipService
import org.seoul.morlatjanghak.scholarship.dto.AppliedScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.AppliedSearchOption
import org.seoul.morlatjanghak.scholarship.dto.ScholarshipDetailResponse
import org.seoul.morlatjanghak.scholarship.dto.ScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.SearchOption
import org.seoul.morlatjanghak.scholarship.dto.StoredScholarshipResponse
import org.seoul.morlatjanghak.scholarship.dto.StoredSearchOption
import org.seoul.morlatjanghak.scholarship.repository.ScholarshipRepository
import org.seoul.morlatjanghak.scholarship.repository.ScholarshipSpecification
import org.seoul.morlatjanghak.storedscholarship.StoredScholarshipRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ScholarshipService(
    private val recommendedScholarshipService: RecommendedScholarshipService,
    private val scholarshipRepository: ScholarshipRepository,
    private val appliedScholarshipRepository: AppliedScholarshipRepository,
    private val storedScholarshipRepository: StoredScholarshipRepository
) {
    fun findAll(memberId: String, pageable: Pageable, searchOption: SearchOption): Page<ScholarshipResponse> {
        val sort = searchOption.getSort()
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, sort)
        val specification = ScholarshipSpecification(searchOption)

        val scholarships = scholarshipRepository.findAll(specification, pageRequest)
            .map(ScholarshipResponse.Companion::of)

        return createScholarshipResponse(memberId, scholarships)
    }

    private fun createScholarshipResponse(
        memberId: String,
        scholarships: Page<ScholarshipResponse>
    ): Page<ScholarshipResponse> {
        val appliedScholarships = appliedScholarshipRepository.findAllByMemberId(memberId)
            .associateBy { it.scholarshipId }
        val storedScholarships = storedScholarshipRepository.findAllByMemberId(memberId)
            .associateBy { it.scholarshipId }

        scholarships.map {
            appliedScholarships[it.id]
                ?.let { appliedScholarship ->
                    it.updateApplyingStatus(appliedScholarship.status)
                }
            storedScholarships[it.id]
                ?.let { _ ->
                    it.updateStored(true)
                }
        }

        return scholarships
    }

    fun findRecommended(pageable: Pageable, searchOption: SearchOption, memberId: String): Page<ScholarshipResponse> {
        val recommendedScholarshipIds = recommendedScholarshipService.findRecommendedScholarshipIds(memberId)
        val scholarships = scholarshipRepository.findAllByIdInOrderBy(recommendedScholarshipIds, pageable)
            .map(ScholarshipResponse.Companion::of)

        return createScholarshipResponse(memberId, scholarships)
    }

    @Transactional
    fun findByIdAndIncreaseViewCount(id: Long): ScholarshipDetailResponse {
        val scholarship = scholarshipRepository.findById(id).orElseThrow()
        scholarship.increaseViewCount()

        return ScholarshipDetailResponse.from(scholarship)
    }

    fun findStored(searchOption: StoredSearchOption, memberId: String): List<StoredScholarshipResponse> {
        val storedScholarships = storedScholarshipRepository.findAllByMemberId(memberId)
            .associateBy { it.scholarshipId }
        val appliedScholarships = appliedScholarshipRepository.findAllByMemberId(memberId)
            .associateBy { it.scholarshipId }

        val response = scholarshipRepository.findAllById(storedScholarships.keys)
            .map {
                StoredScholarshipResponse.of(
                    scholarship = it,
                    storedDate = storedScholarships[it.id]!!.updatedAt!!,
                    applyingStatus = appliedScholarships[it.id]?.status ?: ApplyingStatus.NOTHING
                )
            }

        searchOption.sort(response)
        return response
    }

    fun findApplied(searchOption: AppliedSearchOption, memberId: String): List<AppliedScholarshipResponse> {
        val appliedScholarships = appliedScholarshipRepository.findAllByMemberId(memberId)
            .associateBy { it.scholarshipId }
        val storedScholarships = storedScholarshipRepository.findAllByMemberId(memberId)
            .associateBy { it.scholarshipId }

        val response = scholarshipRepository.findAllById(appliedScholarships.keys)
            .map {
                AppliedScholarshipResponse.of(
                    scholarship = it,
                    storedDate = storedScholarships[it.id]?.updatedAt,
                    applied = appliedScholarships[it.id]!!,
                )
            }

        searchOption.sort(response)
        return response
    }
}
