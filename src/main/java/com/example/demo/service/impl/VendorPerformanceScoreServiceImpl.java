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

    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
            DeliveryEvaluationRepository deliveryEvaluationRepository) {
        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
    }

    // ================= CREATE =================
    @Override
    public VendorPerformanceScore create(VendorPerformanceScore score) {

        if (score.getDeliveryEvaluation() == null
                || score.getDeliveryEvaluation().getId() == null) {
            throw new RuntimeException("DeliveryEvaluation id is required");
        }

        Long evaluationId = score.getDeliveryEvaluation().getId();

        // 1️⃣ Fetch DeliveryEvaluation from DB
        DeliveryEvaluation evaluation = deliveryEvaluationRepository
                .findById(evaluationId)
                .orElseThrow(() ->
                        new RuntimeException("DeliveryEvaluation not found with id " + evaluationId));

        // 2️⃣ Prevent duplicate performance score for same delivery evaluation
        if (vendorPerformanceScoreRepository
                .existsByDeliveryEvaluation_Id(evaluationId)) {
            throw new RuntimeException(
                    "Performance score already exists for this delivery evaluation");
        }

        // 3️⃣ Calculate overall score
        int overallScore =
                (evaluation.getDeliveryScore() + evaluation.getQualityScore()) / 2;

        // 4️⃣ Set values
        score.setDeliveryEvaluation(evaluation);
        score.setOverallScore(overallScore);
        score.setActive(true);

        return vendorPerformanceScoreRepository.save(score);
    }

    // ================= GET ALL =================
    @Override
    public List<VendorPerformanceScore> getAll() {
        return vendorPerformanceScoreRepository.findAll();
    }

    // ================= GET BY ID =================
    @Override
    public VendorPerformanceScore getById(Long id) {
        return vendorPerformanceScoreRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("VendorPerformanceScore not found with id " + id));
    }

    // ================= UPDATE =================
    @Override
    public VendorPerformanceScore update(Long id, VendorPerformanceScore updatedScore) {

        VendorPerformanceScore existing = getById(id);

        if (updatedScore.getDeliveryEvaluation() != null
                && updatedScore.getDeliveryEvaluation().getId() != null) {

            Long evaluationId = updatedScore.getDeliveryEvaluation().getId();

            DeliveryEvaluation evaluation = deliveryEvaluationRepository
                    .findById(evaluationId)
                    .orElseThrow(() ->
                            new RuntimeException("DeliveryEvaluation not found with id " + evaluationId));

            int recalculatedScore =
                    (evaluation.getDeliveryScore() + evaluation.getQualityScore()) / 2;

            existing.setDeliveryEvaluation(evaluation);
            existing.setOverallScore(recalculatedScore);
        }

        existing.setActive(updatedScore.isActive());

        return vendorPerformanceScoreRepository.save(existing);
    }

    // ================= DEACTIVATE =================
    @Override
    public void deactivate(Long id) {
        VendorPerformanceScore score = getById(id);
        score.setActive(false);
        vendorPerformanceScoreRepository.save(score);
    }
}
