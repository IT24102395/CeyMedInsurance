package com.healthinsurancemanagementsystem.CeyMedInsurance.controller;

import com.healthinsurancemanagementsystem.CeyMedInsurance.model.Claim;
import com.healthinsurancemanagementsystem.CeyMedInsurance.service.ClaimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Submit new claim
    @PostMapping
    public Claim submitClaim(@RequestBody Claim claim) {
        return claimService.submitClaim(claim);
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
    public Claim updateClaimStatus(@PathVariable Long id, @RequestParam String status) {
        return claimService.updateClaimStatus(id, status);
    }

    // Delete claim
    @DeleteMapping("/{id}")
    public String deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return "Claim with ID " + id + " deleted successfully.";
    }
}
