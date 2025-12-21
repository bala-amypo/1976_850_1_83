package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository repository;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {
        return repository.save(evaluation);
    }

    @Override
    public DeliveryEvaluation updateEvaluation(Long id, DeliveryEvaluation evaluation) {
        DeliveryEvaluation existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));

        existing.setDeliveryDate(evaluation.getDeliveryDate());
        existing.setDeliveryScore(evaluation.getDeliveryScore());
        existing.setQualityScore(evaluation.getQualityScore());

        return repository.save(existing);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));
    }

    @Override
    public List<DeliveryEvaluation> getAllEvaluations() {
        return repository.findAll();
    }

    @Override
    public void deactivateEvaluation(Long id) {
        DeliveryEvaluation evaluation = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));

        evaluation.setActive(false);
        repository.save(evaluation);
    }
}
