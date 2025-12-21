package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "vendor_performance_score",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "delivery_evaluation_id")
    }
)
public class VendorPerformanceScore {

    // ================= PRIMARY KEY =================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= DERIVED SCORE =================
    @Column(nullable = false)
    private int overallScore;

    // ================= STATUS =================
    @Column(nullable = false)
    private boolean active = true;

    // ================= RELATIONSHIP =================
    /**
     * One-to-One relationship
     * Each DeliveryEvaluation can have ONLY ONE performance score
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "delivery_evaluation_id",
        nullable = false,
        unique = true
    )
    private DeliveryEvaluation deliveryEvaluation;

    // ================= CONSTRUCTORS =================
    public VendorPerformanceScore() {
    }

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DeliveryEvaluation getDeliveryEvaluation() {
        return deliveryEvaluation;
    }

    public void setDeliveryEvaluation(DeliveryEvaluation deliveryEvaluation) {
        this.deliveryEvaluation = deliveryEvaluation;
    }
}
