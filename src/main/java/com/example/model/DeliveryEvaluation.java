package com.example.demo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DeliveryEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long actualDeliveryDays;
    private Double qualityScore;
     private Boolean meetsDeliveryTarget;
     private Boolean meetsQualityTarget;
     
    public DeliveryEvaluation(Long actualDeliveryDays, Double qualityScore, Boolean meetsDeliveryTarget,
            Boolean meetsQualityTarget) {
        this.actualDeliveryDays = actualDeliveryDays;
        this.qualityScore = qualityScore;
        this.meetsDeliveryTarget = meetsDeliveryTarget;
        this.meetsQualityTarget = meetsQualityTarget;
    }

     public DeliveryEvaluation(){
        
     }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getActualDeliveryDays() {
        return actualDeliveryDays;
    }
    public void setActualDeliveryDays(Long actualDeliveryDays) {
        this.actualDeliveryDays = actualDeliveryDays;
    }
    public Double getQualityScore() {
        return qualityScore;
    }
    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }
    public Boolean getMeetsDeliveryTarget() {
        return meetsDeliveryTarget;
    }
    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) {
        this.meetsDeliveryTarget = meetsDeliveryTarget;
    }
    public Boolean getMeetsQualityTarget() {
        return meetsQualityTarget;
    }
    public void setMeetsQualityTarget(Boolean meetsQualityTarget) {
        this.meetsQualityTarget = meetsQualityTarget;
    }
    
    




}
