package com.example.demo.controller;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService evaluationService;

    public DeliveryEvaluationController(DeliveryEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public DeliveryEvaluation create(@RequestBody DeliveryEvaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }

    @GetMapping("/{id}")
    public DeliveryEvaluation getById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<DeliveryEvaluation> getByVendor(@PathVariable Long vendorId) {
        return evaluationService.getEvaluationsForVendor(vendorId);
    }

    @GetMapping("/sla/{slaId}")
    public List<DeliveryEvaluation> getBySla(@PathVariable Long slaId) {
        return evaluationService.getEvaluationsForRequirement(slaId);
    }
}
