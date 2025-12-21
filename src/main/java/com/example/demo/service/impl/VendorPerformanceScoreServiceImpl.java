package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository repository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository repository,
            DeliveryEvaluationRepository deliveryEvaluationRepository) {
        this.repository = repository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
    }

    @Override
    public VendorPerformanceScore create(VendorPerformanceScore score) {

        if (score.getDeliveryEvaluation() == null ||
            score.getDeliveryEvaluation().getId() == null) {
            throw new IllegalArgumentException("DeliveryEvaluation ID is required");
        }

        DeliveryEvaluation evaluation = deliveryEvaluationRepository
                .findById(score.getDeliveryEvaluation().getId())
                .orElseThrow(() -> new RuntimeException("DeliveryEvaluation not found"));

        // ✅ CALCULATE overallScore
        int overallScore =
                (evaluation.getDeliveryScore() + evaluation.getQualityScore()) / 2;

        score.setOverallScore(overallScore);
        score.setDeliveryEvaluation(evaluation);
        score.setActive(true);

        return repository.save(score);
    }

    @Override
    public VendorPerformanceScore getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("VendorPerformanceScore not found"));
    }

    @Override
    public List<VendorPerformanceScore> getAll() {
        return repository.findAll();
    }

    @Override
    public VendorPerformanceScore update(Long id, VendorPerformanceScore updated) {
        VendorPerformanceScore existing = getById(id);

        if (updated.getDeliveryEvaluation() != null &&
            updated.getDeliveryEvaluation().getId() != null) {

            DeliveryEvaluation evaluation = deliveryEvaluationRepository
                    .findById(updated.getDeliveryEvaluation().getId())
                    .orElseThrow(() -> new RuntimeException("DeliveryEvaluation not found"));

            int overallScore =
                    (evaluation.getDeliveryScore() + evaluation.getQualityScore()) / 2;

            existing.setDeliveryEvaluation(evaluation);
            existing.setOverallScore(overallScore);
        }

        return repository.save(existing);
    }

    @Override
    public void deactivate(Long id) {
        VendorPerformanceScore score = getById(id);
        score.setActive(false);
        repository.save(score);
    }
}
