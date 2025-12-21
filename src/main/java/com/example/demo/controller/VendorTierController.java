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
    public VendorTier create(@RequestBody VendorTier tier) {
        return vendorTierService.createVendorTier(tier);
    }

    @PutMapping("/{id}")
    public VendorTier update(@PathVariable Long id, @RequestBody VendorTier tier) {
        return vendorTierService.updateVendorTier(id, tier);
    }

    @GetMapping("/{id}")
    public VendorTier getById(@PathVariable Long id) {
        return vendorTierService.getVendorTierById(id);
    }

    @GetMapping
    public List<VendorTier> getAll() {
        return vendorTierService.getAllVendorTiers();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        vendorTierService.deactivateVendorTier(id);
    }
}
