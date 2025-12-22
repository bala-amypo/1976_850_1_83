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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        name = "sla_name",
        nullable = false,
        unique = true,
        length = 100
    )
    private String slaName;

    @Column(
        nullable = false,
        length = 255
    )
    private String description;

    
    @Column(nullable = false)
    private Integer targetValue;

    
    @Column(nullable = false)
    private Boolean active = true;

   
    /*
    @OneToOne
    @JoinColumn(
        name = "vendor_id",
        nullable = false,
        unique = true
    )
    private Vendor vendor;
    */


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
