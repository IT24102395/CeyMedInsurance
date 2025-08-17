package com.healthinsurancemanagementsystem.CeyMedInsurance.service;

import com.healthinsurancemanagementsystem.CeyMedInsurance.model.Claim;
import com.healthinsurancemanagementsystem.CeyMedInsurance.repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    // Submit a new claim
    public Claim submitClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    // Get all claims
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    // Get claims by user
    public List<Claim> getClaimsByUser(Long userId) {
        return claimRepository.findByUser_UserId(userId);
    }

    // Get claim by ID
    public Optional<Claim> getClaimById(Long claimId) {
        return claimRepository.findById(claimId);
    }

    // Update claim status
    public Claim updateClaimStatus(Long claimId, String status) {
        return claimRepository.findById(claimId)
                .map(claim -> {
                    claim.setStatus(status);
                    return claimRepository.save(claim);
                })
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    // Delete claim (optional, e.g. within deadline)
    public void deleteClaim(Long claimId) {
        claimRepository.deleteById(claimId);
    }
}
