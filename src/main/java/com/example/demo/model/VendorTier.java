package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(
    name = "vendor_tier",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "tierName")
    }
)
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tierName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "vendorTier")
    private List<Vendor> vendors;

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
