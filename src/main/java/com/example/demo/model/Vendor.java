package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;

@Entity
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String contactEmail;
    private String contactPhone;
    
    // Rule: active defaults to true
    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}