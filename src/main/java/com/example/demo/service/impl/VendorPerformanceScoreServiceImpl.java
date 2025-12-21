package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl
        implements VendorPerformanceScoreService {

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

        Long evalId = score.getDeliveryEvaluation().getId();

        DeliveryEvaluation evaluation =
                deliveryEvaluationRepository.findById(evalId)
                        .orElseThrow(() ->
                                new RuntimeException("DeliveryEvaluation not found"));

        if (repository.existsByDeliveryEvaluation_Id(evalId)) {
            throw new RuntimeException("Performance score already exists for this delivery evaluation");
        }

        // Auto-calculate overall score
        int calculatedScore =
                (evaluation.getDeliveryScore() + evaluation.getQualityScore()) / 2;

        score.setOverallScore(calculatedScore);
        score.setDeliveryEvaluation(evaluation);
        score.setActive(true);

        return repository.save(score);
    }

    @Override
    public VendorPerformanceScore getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance score not found"));
    }

    @Override
    public List<VendorPerformanceScore> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        VendorPerformanceScore score = getById(id);
        score.setActive(false);
        repository.save(score);
    }
}
