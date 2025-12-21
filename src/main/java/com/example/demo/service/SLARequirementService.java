package com.example.demo.service;

import com.example.demo.model.Sla;
import java.util.List;

public interface SlaService {

    Sla createSla(Sla sla);

    List<Sla> getAllSlas();
}
