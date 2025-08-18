package com.healthinsurancemanagementsystem.CeyMedInsurance.controller;

import com.healthinsurancemanagementsystem.CeyMedInsurance.dto.ClaimRequest;
import com.healthinsurancemanagementsystem.CeyMedInsurance.model.Claim;
import com.healthinsurancemanagementsystem.CeyMedInsurance.model.ClaimDoc;
import com.healthinsurancemanagementsystem.CeyMedInsurance.model.ClaimStatusHistory;
import com.healthinsurancemanagementsystem.CeyMedInsurance.service.ClaimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Submit new claim using DTO
    @PostMapping
    public Claim submitClaim(@RequestBody ClaimRequest request) {
        return claimService.submitClaim(request);
    }

    // Get all claims
    @GetMapping
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }

    // Get claims by user
    @GetMapping("/user/{userId}")
    public List<Claim> getClaimsByUser(@PathVariable Long userId) {
        return claimService.getClaimsByUser(userId);
    }

    // Get claim by ID
    @GetMapping("/{id}")
    public Optional<Claim> getClaimById(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }

    // Update claim status
    @PatchMapping("/{id}/status")
    public Claim updateClaimStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        return claimService.updateClaimStatus(id, payload.get("status"));
    }

    // Delete claim
    @DeleteMapping("/{id}")
    public String deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return "Claim with ID " + id + " deleted successfully.";
    }

    // Add document
    @PostMapping("/{id}/documents")
    public ClaimDoc addDocument(@PathVariable Long id, @RequestBody ClaimDoc doc) {
        return claimService.addDocument(id, doc);
    }

    // Get claim documents
    @GetMapping("/{id}/documents")
    public List<ClaimDoc> getDocuments(@PathVariable Long id) {
        return claimService.getDocuments(id);
    }

    // Get claim status history
    @GetMapping("/{id}/history")
    public List<ClaimStatusHistory> getHistory(@PathVariable Long id) {
        return claimService.getHistory(id);
    }
}
