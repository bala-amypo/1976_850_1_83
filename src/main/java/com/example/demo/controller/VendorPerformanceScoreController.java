package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance-scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService scoreService;

    public VendorPerformanceScoreController(VendorPerformanceScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/{vendorId}")
    public VendorPerformanceScore calculate(@PathVariable Long vendorId) {
        return scoreService.calculateScore(vendorId);
    }

    @GetMapping("/{vendorId}/latest")
    public VendorPerformanceScore getLatest(@PathVariable Long vendorId) {
        return scoreService.getLatestScore(vendorId);
    }

    @GetMapping("/{vendorId}")
    public List<VendorPerformanceScore> getHistory(@PathVariable Long vendorId) {
        return scoreService.getScoresForVendor(vendorId);
    }
}
