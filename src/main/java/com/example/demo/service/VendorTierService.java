package com.example.demo.service;

import com.example.demo.model.VendorTier;
import java.util.List;

public interface VendorTierService {

    VendorTier create(VendorTier vendorTier);

    List<VendorTier> getAll();

    VendorTier getById(Long id);

    void deactivate(Long id);
}
