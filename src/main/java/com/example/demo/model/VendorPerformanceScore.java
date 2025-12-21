package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "vendor_performance_score",
    uniqueConstraints = @UniqueConstraint(columnNames = "delivery_evaluation_id")
)
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer overallScore;

    @Column(nullable = false)
    private boolean active = true;

    @OneToOne(optional = false)
    @JoinColumn(
        name = "delivery_evaluation_id",
        nullable = false,
        unique = true
    )
    private DeliveryEvaluation deliveryEvaluation;

    // getters & setters
}
