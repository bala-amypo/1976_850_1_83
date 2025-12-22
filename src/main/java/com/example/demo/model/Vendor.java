package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
    name = "vendor",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "contactEmail")
    }
)
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String contactEmail;

    @NotBlank
    @Column(nullable = false)
    private String contactPhone;

    @Column(nullable = false)
    private boolean active = true;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_tier_id", nullable = false)
    private VendorTier vendorTier;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public VendorTier getVendorTier() {
        return vendorTier;
    }

    public void setVendorTier(VendorTier vendorTier) {
        this.vendorTier = vendorTier;
    }
}
