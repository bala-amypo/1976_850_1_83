package com.example.demo.service;

import com.example.demo.model.VendorTier;
import java.util.List;

public interface VendorTierService {

    VendorTier createVendorTier(VendorTier tier);

    VendorTier updateVendorTier(Long id, VendorTier tier);

    VendorTier getVendorTierById(Long id);

    List<VendorTier> getAllVendorTiers();

    void deactivateVendorTier(Long id);
}
