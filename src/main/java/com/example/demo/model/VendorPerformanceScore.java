package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "vendor_performance_scores",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "delivery_evaluation_id")
    }
)
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int overallScore;

    @Column(nullable = false)
    private boolean active;

    @OneToOne
    @JoinColumn(
        name = "delivery_evaluation_id",
        nullable = false,
        unique = true
    )
    private DeliveryEvaluation deliveryEvaluation;

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public boolean isActive() {
        return active;
    }

    public DeliveryEvaluation getDeliveryEvaluation() {
        return deliveryEvaluation;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDeliveryEvaluation(DeliveryEvaluation deliveryEvaluation) {
        this.deliveryEvaluation = deliveryEvaluation;
    }
}
