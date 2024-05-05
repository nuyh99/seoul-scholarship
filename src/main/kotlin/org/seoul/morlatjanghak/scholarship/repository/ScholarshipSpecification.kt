package org.seoul.morlatjanghak.scholarship.repository

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.seoul.morlatjanghak.scholarship.domain.Scholarship
import org.seoul.morlatjanghak.scholarship.dto.SearchOption
import org.springframework.data.jpa.domain.Specification

class ScholarshipSpecification(
    private val searchOption: SearchOption
) : Specification<Scholarship> {

    override fun toPredicate(
        root: Root<Scholarship>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        if (searchOption.keyword.isNullOrEmpty()) {
            return criteriaBuilder.conjunction()
        }

        return criteriaBuilder.or(
            criteriaBuilder.like(root.get("organization"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("productName"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("organizationType"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("universityCategory"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("grade"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("majorCategory"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("gradeDetails"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("incomeDetails"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("supportDetails"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("specificQualificationDetails"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("localResidencyDetails"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("selectionMethodDetails"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("eligibilityRestrictionDetails"), "%${searchOption.keyword}%"),
            criteriaBuilder.like(root.get("recommendationRequiredDetails"), "%${searchOption.keyword}%"),
        )
    }
}
