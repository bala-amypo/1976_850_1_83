package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository vendorTierRepository;

    public VendorTierServiceImpl(VendorTierRepository vendorTierRepository) {
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorTier createVendorTier(VendorTier tier) {

        if (vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }

        tier.setActive(true);
        return vendorTierRepository.save(tier);
    }

    @Override
    public VendorTier updateVendorTier(Long id, VendorTier tier) {

        VendorTier existing = vendorTierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VendorTier not found"));

        if (!existing.getTierName().equals(tier.getTierName())
                && vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }

        existing.setTierName(tier.getTierName());
        existing.setDescription(tier.getDescription());

        return vendorTierRepository.save(existing);
    }

    @Override
    public VendorTier getVendorTierById(Long id) {
        return vendorTierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VendorTier not found"));
    }

    @Override
    public List<VendorTier> getAllVendorTiers() {
        return vendorTierRepository.findAll();
    }

    @Override
    public void deactivateVendorTier(Long id) {
        VendorTier tier = getVendorTierById(id);
        tier.setActive(false);
        vendorTierRepository.save(tier);
    }
}
