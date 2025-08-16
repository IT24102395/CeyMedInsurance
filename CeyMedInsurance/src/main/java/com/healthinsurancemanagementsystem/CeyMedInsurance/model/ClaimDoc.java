package com.healthinsurancemanagementsystem.CeyMedInsurance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claim_docs")
public class ClaimDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;

    @ManyToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    private String fileUrl;
    private String type;
    private LocalDateTime uploadedAt = LocalDateTime.now();

    // Getters and Setters
}
