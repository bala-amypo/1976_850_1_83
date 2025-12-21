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
        throw new RuntimeException("DeliveryEvaluation ID is required");
    }

    Long deliveryId = score.getDeliveryEvaluation().getId();

    DeliveryEvaluation delivery = deliveryEvaluationRepository.findById(deliveryId)
            .orElseThrow(() -> new RuntimeException("DeliveryEvaluation not found"));

    // Calculate overall score automatically
    int calculatedScore =
            (delivery.getDeliveryScore() + delivery.getQualityScore()) / 2;

    score.setOverallScore(calculatedScore);
    score.setDeliveryEvaluation(delivery);
    score.setActive(true);

    return vendorPerformanceScoreRepository.save(score);
}


    @Override
    public VendorPerformanceScore update(Long id, VendorPerformanceScore updated) {

        VendorPerformanceScore existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PerformanceScore not found"));

        if (updated.getDeliveryEvaluation() != null) {
            Long deliveryId = updated.getDeliveryEvaluation().getId();

            DeliveryEvaluation delivery = deliveryEvaluationRepository.findById(deliveryId)
                    .orElseThrow(() -> new RuntimeException("DeliveryEvaluation not found"));

            existing.setDeliveryEvaluation(delivery);
        }

        existing.setOverallScore(updated.getOverallScore());
        existing.setActive(updated.isActive());

        return repository.save(existing);
    }

    @Override
    public VendorPerformanceScore getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PerformanceScore not found"));
    }

    @Override
    public List<VendorPerformanceScore> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        VendorPerformanceScore score = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PerformanceScore not found"));

        score.setActive(false);
        repository.save(score);
    }
}
