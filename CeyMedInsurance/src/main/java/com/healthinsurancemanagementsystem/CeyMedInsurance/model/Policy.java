package com.healthinsurancemanagementsystem.CeyMedInsurance.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "policies")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal coverageAmount;

    private BigDecimal premium;

    private Integer durationMonths;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
}
