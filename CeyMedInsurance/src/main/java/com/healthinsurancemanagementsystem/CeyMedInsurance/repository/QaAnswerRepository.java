package com.healthinsurancemanagementsystem.CeyMedInsurance.repository;

import com.healthinsurancemanagementsystem.CeyMedInsurance.model.QaAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QaAnswerRepository extends JpaRepository<QaAnswer, Long> {
    List<QaAnswer> findByQuestion_QuestionId(Long questionId);
    List<QaAnswer> findByUser_UserId(Long userId);
}
