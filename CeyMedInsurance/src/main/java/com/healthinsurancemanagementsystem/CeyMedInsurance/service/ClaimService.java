package com.healthinsurancemanagementsystem.CeyMedInsurance.service;

import com.healthinsurancemanagementsystem.CeyMedInsurance.dto.ClaimRequest;
import com.healthinsurancemanagementsystem.CeyMedInsurance.model.*;
import com.healthinsurancemanagementsystem.CeyMedInsurance.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;
    private final ClaimDocRepository claimDocRepository;
    private final ClaimStatusHistoryRepository claimStatusHistoryRepository;
    private final UserRepository userRepository;
    private final PolicyRepository policyRepository;

    public ClaimService(ClaimRepository claimRepository,
                        ClaimDocRepository claimDocRepository,
                        ClaimStatusHistoryRepository claimStatusHistoryRepository,
                        UserRepository userRepository,
                        PolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.claimDocRepository = claimDocRepository;
        this.claimStatusHistoryRepository = claimStatusHistoryRepository;
        this.userRepository = userRepository;
        this.policyRepository = policyRepository;
    }

    // Submit claim using DTO
    public Claim submitClaim(ClaimRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Policy policy = policyRepository.findById(request.getPolicyId())
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        Claim claim = new Claim();
        claim.setUser(user);
        claim.setPolicy(policy);
        claim.setAmount(request.getAmount());
        claim.setStatus("Pending");
        claim.setSubmittedAt(LocalDateTime.now());

        Claim saved = claimRepository.save(claim);

        // Create initial history record
        ClaimStatusHistory history = new ClaimStatusHistory();
        history.setClaim(saved);
        history.setStatus("Pending");
        history.setNotes("Claim submitted");
        history.setChangedAt(LocalDateTime.now());
        claimStatusHistoryRepository.save(history);

        return saved;
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
    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    // Update claim status + log in history
    public Claim updateClaimStatus(Long id, String status) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        claim.setStatus(status);

        Claim updated = claimRepository.save(claim);

        ClaimStatusHistory history = new ClaimStatusHistory();
        history.setClaim(updated);
        history.setStatus(status);
        history.setNotes("Status updated to " + status);
        history.setChangedAt(LocalDateTime.now());
        claimStatusHistoryRepository.save(history);

        return updated;
    }

    // Delete claim
    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }

    // Add supporting document
    public ClaimDoc addDocument(Long claimId, ClaimDoc doc) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        doc.setClaim(claim);
        doc.setUploadedAt(LocalDateTime.now());
        return claimDocRepository.save(doc);
    }

    // Get documents for a claim
    public List<ClaimDoc> getDocuments(Long claimId) {
        return claimDocRepository.findByClaim_ClaimId(claimId);
    }

    // Get claim history
    public List<ClaimStatusHistory> getHistory(Long claimId) {
        return claimStatusHistoryRepository.findByClaim_ClaimId(claimId);
    }
}
