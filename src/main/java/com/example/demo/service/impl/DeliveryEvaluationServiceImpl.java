package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.DeliveryEvaluationService;

import java.util.List;

public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRequirementRepository;

    // ✅ Constructor injection (MANDATORY)
    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository deliveryEvaluationRepository,
            VendorRepository vendorRepository,
            SLARequirementRepository slaRequirementRepository) {

        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

        // 1️⃣ Fetch vendor
        Vendor vendor = vendorRepository.findById(evaluation.getVendor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        // 2️⃣ Vendor must be active
        if (!vendor.getActive()) {
            throw new IllegalStateException("Only active vendors allowed");
        }

        // 3️⃣ Validate delivery days
        if (evaluation.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException("Delivery days must be >= 0");
        }

        // 4️⃣ Validate quality score
        if (evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }

        // 5️⃣ Fetch SLA requirement
        SLARequirement sla = slaRequirementRepository
                .findById(evaluation.getSlaRequirement().getId())
                .orElseThrow(() -> new IllegalArgumentException("Requirement not found"));

        // 6️⃣ Calculate flags
        evaluation.setMeetsDeliveryTarget(
                evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays()
        );

        evaluation.setMeetsQualityTarget(
                evaluation.getQualityScore() >= sla.getMinQualityScore()
        );

        return deliveryEvaluationRepository.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return deliveryEvaluationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return deliveryEvaluationRepository.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return deliveryEvaluationRepository.findBySlaRequirementId(requirementId);
    }
}
