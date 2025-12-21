package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {

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

    // getters & setters
}
