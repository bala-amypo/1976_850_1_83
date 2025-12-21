package com.example.demo.service;

import com.example.demo.model.VendorPerformanceScore;
import java.util.List;

public interface VendorPerformanceScoreService {

    VendorPerformanceScore create(VendorPerformanceScore score);

    VendorPerformanceScore getById(Long id);

    List<VendorPerformanceScore> getAll();

    VendorPerformanceScore update(Long id, VendorPerformanceScore score); // ✅ ADD THIS

    void deactivate(Long id);
}
