package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "sla_requirement",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sla_name"})
    }
)
public class SLARequirement {

    // =========================
    // PRIMARY KEY
    // =========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // SLA NAME (UNIQUE)
    // =========================
    @Column(
        name = "sla_name",
        nullable = false,
        unique = true,
        length = 100
    )
    private String slaName;

    // =========================
    // DESCRIPTION
    // =========================
    @Column(
        nullable = false,
        length = 255
    )
    private String description;

    // =========================
    // TARGET VALUE
    // =========================
    @Column(nullable = false)
    private Integer targetValue;

    // =========================
    // ACTIVE FLAG
    // =========================
    @Column(nullable = false)
    private Boolean active = true;

    // =========================
    // OPTIONAL RELATIONSHIP
    // (Example: One SLA per Vendor)
    // Uncomment ONLY if your document mentions it
    // =========================
    /*
    @OneToOne
    @JoinColumn(
        name = "vendor_id",
        nullable = false,
        unique = true
    )
    private Vendor vendor;
    */

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlaName() {
        return slaName;
    }

    public void setSlaName(String slaName) {
        this.slaName = slaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Integer targetValue) {
        this.targetValue = targetValue;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /*
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    */
}
