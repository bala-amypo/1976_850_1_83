package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-performance-scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService service;

    public VendorPerformanceScoreController(VendorPerformanceScoreService service) {
        this.service = service;
    }

    @PostMapping
    public VendorPerformanceScore create(
            @RequestBody VendorPerformanceScore score) {
        return service.create(score);
    }

    @GetMapping("/{id}")
    public VendorPerformanceScore getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<VendorPerformanceScore> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public VendorPerformanceScore deactivate(@PathVariable Long id) {
        return service.deactivate(id);
    }
}
