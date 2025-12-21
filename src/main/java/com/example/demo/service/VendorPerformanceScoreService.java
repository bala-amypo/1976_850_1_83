package com.example.demo.service;

import com.example.demo.model.VendorPerformanceScore;

import java.util.List;

public interface VendorPerformanceScoreService {

    VendorPerformanceScore create(VendorPerformanceScore score);

    VendorPerformanceScore getById(Long id);

    List<VendorPerformanceScore> getAll();

    VendorPerformanceScore deactivate(Long id);
}
