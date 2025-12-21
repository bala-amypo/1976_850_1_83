package com.example.demo.repository;

import com.example.demo.model.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorPerformanceScoreRepository
        extends JpaRepository<VendorPerformanceScore, Long> {

    Optional<VendorPerformanceScore> findByDeliveryEvaluationId(Long deliveryEvaluationId);
}
