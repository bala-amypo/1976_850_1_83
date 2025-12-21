package com.example.demo.service;

import com.example.demo.model.SLARequirement;
import java.util.List;

public interface SLARequirementService {

    SLARequirement createSLA(SLARequirement sla);

    SLARequirement updateSLA(Long id, SLARequirement sla);

    SLARequirement getSLAById(Long id);

    List<SLARequirement> getAllSLAs();
}
