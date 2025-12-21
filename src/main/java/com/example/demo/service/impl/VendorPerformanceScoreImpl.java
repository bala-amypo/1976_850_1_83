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
    private final DeliveryEvaluationRepository evaluationRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepository,
            DeliveryEvaluationRepository evaluationRepository) {
        this.scoreRepository = scoreRepository;
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public VendorPerformanceScore create(VendorPerformanceScore score) {

        Long evalId = score.getDeliveryEvaluation().getId();

        DeliveryEvaluation evaluation = evaluationRepository.findById(evalId)
                .orElseThrow(() -> new RuntimeException("Delivery evaluation not found"));

        scoreRepository.findByDeliveryEvaluationId(evalId)
                .ifPresent(s -> {
                    throw new IllegalArgumentException(
                            "Performance score already exists for this evaluation");
                });

        int overall =
                (evaluation.getDeliveryScore() + evaluation.getQualityScore()) / 2;

        score.setOverallScore(overall);
        score.setDeliveryEvaluation(evaluation);
        score.setActive(true);

        return scoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Score not found"));
    }

    @Override
    public List<VendorPerformanceScore> getAll() {
        return scoreRepository.findAll();
    }

    @Override
    public VendorPerformanceScore deactivate(Long id) {
        VendorPerformanceScore score = getById(id);
        score.setActive(false);
        return scoreRepository.save(score);
    }
}
