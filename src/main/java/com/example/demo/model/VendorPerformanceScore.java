package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "performance_scores",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "delivery_evaluation_id")
    }
)
public class PerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int overallScore;

    @Column(nullable = false)
    private boolean active = true;

    @OneToOne
    @JoinColumn(
        name = "delivery_evaluation_id",
        nullable = false,
        unique = true
    )
    private DeliveryEvaluation deliveryEvaluation;

    // getters and setters

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
