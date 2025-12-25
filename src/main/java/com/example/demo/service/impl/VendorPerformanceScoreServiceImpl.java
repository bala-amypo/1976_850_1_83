package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;

import java.util.List;

public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository vendorTierRepository;

    // ✅ Constructor injection (ORDER MATTERS)
    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
            DeliveryEvaluationRepository deliveryEvaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository vendorTierRepository) {

        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations =
                deliveryEvaluationRepository.findByVendorId(vendorId);

        if (evaluations.isEmpty()) {
            VendorPerformanceScore score =
                    new VendorPerformanceScore(vendor, 0.0, 0.0, 0.0);
            return vendorPerformanceScoreRepository.save(score);
        }

        long onTimeCount = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsDeliveryTarget)
                .count();

        long qualityCount = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsQualityTarget)
                .count();

        double total = evaluations.size();

        double onTimePercentage = (onTimeCount / total) * 100;
        double qualityPercentage = (qualityCount / total) * 100;
        double overallScore = (onTimePercentage + qualityPercentage) / 2;

        VendorPerformanceScore score =
                new VendorPerformanceScore(vendor,
                        onTimePercentage,
                        qualityPercentage,
                        overallScore);

        return vendorPerformanceScoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return vendorPerformanceScoreRepository
                .findByVendorOrderByCalculatedAtDesc(vendorId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository
                .findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
