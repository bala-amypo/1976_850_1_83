package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-performance-scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService service;

    public VendorPerformanceScoreController(
            VendorPerformanceScoreService service) {
        this.service = service;
    }

    @PostMapping
    public VendorPerformanceScore create(
            @RequestBody VendorPerformanceScore score) {
        return service.create(score);
    }

    @GetMapping
    public List<VendorPerformanceScore> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VendorPerformanceScore getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }
}
