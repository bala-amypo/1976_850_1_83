package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;
    private Timestamp calculatedAt = new Timestamp(System.currentTimeMillis());

    public VendorPerformanceScore() {}

    public VendorPerformanceScore(Vendor v, Double o, Double q, Double overall) {
        this.vendor = v;
        this.onTimePercentage = o;
        this.qualityCompliancePercentage = q;
        this.overallScore = overall;
    }

    public Double getOnTimePercentage() { return onTimePercentage; }
    public Double getQualityCompliancePercentage() { return qualityCompliancePercentage; }
    public Double getOverallScore() { return overallScore; }
}
