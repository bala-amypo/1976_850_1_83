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

    private final VendorPerformanceScoreRepository scoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepository,
            DeliveryEvaluationRepository deliveryEvaluationRepository) {
        this.scoreRepository = scoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
    }

    @Override
    public VendorPerformanceScore create(VendorPerformanceScore score) {

        Long deliveryEvalId = score.getDeliveryEvaluation().getId();

        DeliveryEvaluation evaluation = deliveryEvaluationRepository
                .findById(deliveryEvalId)
                .orElseThrow(() -> new RuntimeException("DeliveryEvaluation not found"));

        if (scoreRepository.existsByDeliveryEvaluationId(deliveryEvalId)) {
            throw new IllegalArgumentException(
                    "Performance score already exists for this delivery evaluation");
        }

        score.setDeliveryEvaluation(evaluation);

        
        int overall =
                (evaluation.getDeliveryScore() + evaluation.getQualityScore()) / 2;

        score.setOverallScore(overall);
        score.setActive(true);

        return scoreRepository.save(score);
    }

    @Override
    public List<VendorPerformanceScore> getAll() {
        return scoreRepository.findAll();
    }

    @Override
    public VendorPerformanceScore getById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance score not found"));
    }

    @Override
    public void deactivate(Long id) {
        VendorPerformanceScore score = getById(id);
        score.setActive(false);
        scoreRepository.save(score);
    }
}
