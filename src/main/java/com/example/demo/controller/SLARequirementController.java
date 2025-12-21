package com.example.demo.controller;

import com.example.demo.model.Sla;
import com.example.demo.service.SlaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slas")
public class SlaController {

    private final SlaService slaService;

    public SlaController(SlaService slaService) {
        this.slaService = slaService;
    }

    @PostMapping
    public Sla create(@RequestBody Sla sla) {
        return slaService.createSla(sla);
    }

    @GetMapping
    public List<Sla> getAll() {
        return slaService.getAllSlas();
    }
}
