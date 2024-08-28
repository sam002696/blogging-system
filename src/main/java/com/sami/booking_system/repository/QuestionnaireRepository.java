package com.sami.booking_system.repository;

import com.sami.booking_system.entity.LightEngineeringQuestionnaire.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
    @Query(value = """
        SELECT q FROM Questionnaire q
        WHERE (:search IS NULL OR :search = '' 
               OR LOWER(q.generalInfo.industryName) LIKE LOWER(CONCAT('%', :search, '%')) 
               OR LOWER(q.generalInfo.location) LIKE LOWER(CONCAT('%', :search, '%')) 
               OR LOWER(q.generalInfo.address) LIKE LOWER(CONCAT('%', :search, '%')))
        """,
            countQuery = """
        SELECT COUNT(q) FROM Questionnaire q
        WHERE (:search IS NULL OR :search = '' 
               OR LOWER(q.generalInfo.industryName) LIKE LOWER(CONCAT('%', :search, '%')) 
               OR LOWER(q.generalInfo.location) LIKE LOWER(CONCAT('%', :search, '%')) 
               OR LOWER(q.generalInfo.address) LIKE LOWER(CONCAT('%', :search, '%')))
        """)
    Page<Questionnaire> search(String search, Pageable pageable);
}
