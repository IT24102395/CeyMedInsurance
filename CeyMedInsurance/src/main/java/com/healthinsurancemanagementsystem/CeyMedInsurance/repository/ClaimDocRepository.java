package com.healthinsurancemanagementsystem.CeyMedInsurance.repository;

import com.healthinsurancemanagementsystem.CeyMedInsurance.model.ClaimDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimDocRepository extends JpaRepository<ClaimDoc, Long> {
    List<ClaimDoc> findByClaim_ClaimId(Long claimId);
}
