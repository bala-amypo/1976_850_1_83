package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-tiers")
public class VendorTierController {

    private final VendorTierService vendorTierService;

    public VendorTierController(VendorTierService vendorTierService) {
        this.vendorTierService = vendorTierService;
    }

    @PostMapping
    public VendorTier create(@RequestBody VendorTier vendorTier) {
        return vendorTierService.create(vendorTier);
    }

    @GetMapping
    public List<VendorTier> getAll() {
        return vendorTierService.getAll();
    }

    @GetMapping("/{id}")
    public VendorTier getById(@PathVariable Long id) {
        return vendorTierService.getById(id);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        vendorTierService.deactivate(id);
    }
}
