package com.healthinsurancemanagementsystem.CeyMedInsurance.repository;

import com.healthinsurancemanagementsystem.CeyMedInsurance.model.QaQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QaQuestionRepository extends JpaRepository<QaQuestion, Long> {
    List<QaQuestion> findByUser_UserId(Long userId);
}
