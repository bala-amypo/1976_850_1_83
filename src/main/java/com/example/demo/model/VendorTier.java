package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tierName;

    private Double minScoreThreshold;
    private String description;
    private Boolean active = true;

    public VendorTier() {}

    public VendorTier(String name, Double threshold, String desc) {
        this.tierName = name;
        this.minScoreThreshold = threshold;
        this.description = desc;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
