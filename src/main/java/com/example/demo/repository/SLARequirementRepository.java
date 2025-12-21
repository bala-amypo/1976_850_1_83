package com.example.demo.repository;

import com.example.demo.model.Sla;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlaRepository extends JpaRepository<Sla, Long> {
}
