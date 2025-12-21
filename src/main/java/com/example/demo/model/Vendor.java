package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "vendors",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
public class Vendor {

    // 🔑 PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔒 UNIQUE + NOT NULL
    @Column(nullable = false, unique = true)
    private String name;

    // OPTIONAL FIELDS
    @Column(nullable = true)
    private String contactEmail;

    @Column(nullable = true)
    private String contactPhone;

    // DEFAULT TRUE
    @Column(nullable = false)
    private Boolean active = true;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
