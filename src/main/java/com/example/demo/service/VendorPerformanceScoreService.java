package com.example.demo.service;

import com.example.demo.model.VendorPerformanceScore;

import java.util.List;

public interface VendorPerformanceScoreService {

    VendorPerformanceScore create(VendorPerformanceScore score);

    List<VendorPerformanceScore> getAll();

    VendorPerformanceScore getById(Long id);

    void deactivate(Long id);
}
