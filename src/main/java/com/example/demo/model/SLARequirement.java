package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sla")
public class Sla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int expectedDeliveryDays;

    @Column(nullable = false)
    private int qualityScoreMin;

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getExpectedDeliveryDays() { return expectedDeliveryDays; }

    public void setExpectedDeliveryDays(int expectedDeliveryDays) {
        this.expectedDeliveryDays = expectedDeliveryDays;
    }

    public int getQualityScoreMin() { return qualityScoreMin; }

    public void setQualityScoreMin(int qualityScoreMin) {
        this.qualityScoreMin = qualityScoreMin;
    }
}
