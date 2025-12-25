package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SLARequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String requirementName;

    private String description;
    private Integer maxDeliveryDays;
    private Double minQualityScore;
    private Boolean active = true;

    public SLARequirement() {
        // default constructor
    }

    public SLARequirement(String requirementName, String description,
                          Integer maxDeliveryDays, Double minQualityScore) {
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.minQualityScore = minQualityScore;
        this.active = true;
    }

    // ===== Getters and Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaxDeliveryDays() {
        return maxDeliveryDays;
    }

    public Double getMinQualityScore() {
        return minQualityScore;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
