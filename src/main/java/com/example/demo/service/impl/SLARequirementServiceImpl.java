package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLARequirementServiceImpl
        implements SLARequirementService {

    private final SLARequirementRepository repository;

    public SLARequirementServiceImpl(
            SLARequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public SLARequirement createSLA(SLARequirement sla) {
        return repository.save(sla);
    }

    @Override
    public SLARequirement updateSLA(Long id, SLARequirement sla) {
        SLARequirement existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("SLA not found"));

        existing.setRequirement(sla.getRequirement());
        existing.setTargetValue(sla.getTargetValue());

        return repository.save(existing);
    }

    @Override
    public SLARequirement getSLAById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("SLA not found"));
    }

    @Override
    public List<SLARequirement> getAllSLAs() {
        return repository.findAll();
    }
}
