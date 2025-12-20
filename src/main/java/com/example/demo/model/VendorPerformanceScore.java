package com.example.demo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class VendorPerformanceScore {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;
          
          private Double onTimePercentage;
          private Double qualityCompliancePercentage;
          private Double overallScore;

        public VendorPerformanceScore(Double onTimePercentage, Double qualityCompliancePercentage,
                Double overallScore) {
            this.onTimePercentage = onTimePercentage;
            this.qualityCompliancePercentage = qualityCompliancePercentage;
            this.overallScore = overallScore;
        }

        public VendorPerformanceScore(){

        }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOnTimePercentage() {
        return onTimePercentage;
    }

    public void setOnTimePercentage(Double onTimePercentage) {
        this.onTimePercentage = onTimePercentage;
    }

    public Double getQualityCompliancePercentage() {
        return qualityCompliancePercentage;
    }

    public void setQualityCompliancePercentage(Double qualityCompliancePercentage) {
        this.qualityCompliancePercentage = qualityCompliancePercentage;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }
         

}
