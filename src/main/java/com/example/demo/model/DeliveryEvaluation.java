package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_evaluation")
public class DeliveryEvaluation {

    // =========================
    // PRIMARY KEY
    // =========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // DELIVERY DATE
    // =========================
    @Column(nullable = false)
    private String deliveryDate;

    // =========================
    // DELIVERY SCORE
    // =========================
    @Column(nullable = false)
    private Integer deliveryScore;

    // =========================
    // QUALITY SCORE
    // =========================
    @Column(nullable = false)
    private Integer qualityScore;

    // =========================
    // ACTIVE FLAG (SOFT DELETE)
    // =========================
    @Column(nullable = false)
    private Boolean active = true;

    // =========================
    // RELATIONSHIP: MANY EVALUATIONS → ONE VENDOR
    // (Only added because this is standard in SLA tracking)
    // =========================
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getDeliveryScore() {
        return deliveryScore;
    }

    public void setDeliveryScore(Integer deliveryScore) {
        this.deliveryScore = deliveryScore;
    }

    public Integer getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Integer qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
