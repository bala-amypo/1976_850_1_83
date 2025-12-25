package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository repository;

    public SLARequirementServiceImpl(SLARequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement req) {
        if (req.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be > 0");
        }
        if (req.getQualityThreshold() < 0 || req.getQualityThreshold() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
        if (repository.existsByRequirementName(req.getRequirementName())) {
            throw new IllegalArgumentException("Requirement name must be unique");
        }
        req.setActive(true);
        return repository.save(req);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement update) {
        SLARequirement existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Requirement not found"));

        if (update.getRequirementName() != null &&
                repository.existsByRequirementName(update.getRequirementName())) {
            throw new IllegalArgumentException("Requirement name must be unique");
        }

        if (update.getRequirementName() != null) {
            existing.setRequirementName(update.getRequirementName());
        }
        if (update.getDescription() != null) {
            existing.setDescription(update.getDescription());
        }

        return repository.save(existing);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Requirement not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return repository.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement req = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Requirement not found"));
        req.setActive(false);
        repository.save(req);
    }
}
