package com.example.demo.controller;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
public class SLARequirementController {

    private final SLARequirementService slaRequirementService;

    public SLARequirementController(SLARequirementService slaRequirementService) {
        this.slaRequirementService = slaRequirementService;
    }

    @PostMapping
    public SLARequirement create(@RequestBody SLARequirement sla) {
        return slaRequirementService.createSLA(sla);
    }

    @PutMapping("/{id}")
    public SLARequirement update(@PathVariable Long id,
                                 @RequestBody SLARequirement sla) {
        return slaRequirementService.updateSLA(id, sla);
    }

    @GetMapping("/{id}")
    public SLARequirement getById(@PathVariable Long id) {
        return slaRequirementService.getSLAById(id);
    }

    @GetMapping
    public List<SLARequirement> getAll() {
        return slaRequirementService.getAllSLAs();
    }
}
