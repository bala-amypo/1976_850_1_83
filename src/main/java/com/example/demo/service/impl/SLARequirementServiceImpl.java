package com.example.demo.service.impl;

import com.example.demo.model.Sla;
import com.example.demo.repository.SlaRepository;
import com.example.demo.service.SlaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlaServiceImpl implements SlaService {

    private final SlaRepository slaRepository;

    public SlaServiceImpl(SlaRepository slaRepository) {
        this.slaRepository = slaRepository;
    }

    @Override
    public Sla createSla(Sla sla) {
        return slaRepository.save(sla);
    }

    @Override
    public List<Sla> getAllSlas() {
        return slaRepository.findAll();
    }
}
