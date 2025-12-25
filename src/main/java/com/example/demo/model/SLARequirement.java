package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SLARequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requirementName;
    private String description;
    private Integer maxDeliveryDays;
    private Double qualityThreshold;
    private Boolean active = true;

    public SLARequirement() {
    }

    public SLARequirement(String requirementName,
                          String description,
                          Integer maxDeliveryDays,
                          Double qualityThreshold) {
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.qualityThreshold = qualityThreshold;
        this.active = true;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxDeliveryDays() {
        return maxDeliveryDays;
    }

    public void setMaxDeliveryDays(Integer maxDeliveryDays) {
        this.maxDeliveryDays = maxDeliveryDays;
    }

    public Double getQualityThreshold() {
        return qualityThreshold;
    }

    public void setQualityThreshold(Double qualityThreshold) {
        this.qualityThreshold = qualityThreshold;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
