package com.healthinsurancemanagementsystem.CeyMedInsurance.dto;

import java.math.BigDecimal;

public class ClaimRequest {
    private Long userId;
    private Long policyId;
    private BigDecimal amount;

    // Getters & Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getPolicyId() { return policyId; }
    public void setPolicyId(Long policyId) { this.policyId = policyId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
