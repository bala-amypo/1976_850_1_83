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
    public VendorTier create(VendorTier vendorTier) {

        if (vendorTierRepository.existsByTierName(vendorTier.getTierName())) {
            throw new IllegalArgumentException("Tier name already exists");
        }

        return vendorTierRepository.save(vendorTier);
    }

    @Override
    public List<VendorTier> getAll() {
        return vendorTierRepository.findAll();
    }

    @Override
    public VendorTier getById(Long id) {
        return vendorTierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor tier not found"));
    }

    @Override
    public void deactivate(Long id) {
        VendorTier tier = getById(id);
        tier.setActive(false);
        vendorTierRepository.save(tier);
    }
}
