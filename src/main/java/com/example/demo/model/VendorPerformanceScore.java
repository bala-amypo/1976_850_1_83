package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "overall_score", nullable = false)
    private Integer overallScore;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToOne
    @JoinColumn(name = "delivery_evaluation_id", nullable = false, unique = true)
    private DeliveryEvaluation deliveryEvaluation;

    // getters & setters
    public Long getId() {
        return id;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DeliveryEvaluation getDeliveryEvaluation() {
        return deliveryEvaluation;
    }

    public void setDeliveryEvaluation(DeliveryEvaluation deliveryEvaluation) {
        this.deliveryEvaluation = deliveryEvaluation;
    }
}
