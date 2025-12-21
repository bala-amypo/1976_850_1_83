package com.example.demo.service.impl;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;

    // Must use Constructor Injection
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        // Rule: Check duplicate name
        if (vendorRepository.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("Vendor name must be unique"); // Keyword "unique" required
        }
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendor not found")); // Keyword "not found" required
    }
}