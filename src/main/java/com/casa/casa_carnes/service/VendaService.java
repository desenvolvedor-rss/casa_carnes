package com.casa.casa_carnes.service;

import com.casa.casa_carnes.repositories.VendaModelRepository;
import com.casa.casa_carnes.models.VendaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaModelRepository vendaRepository;

    public List<VendaModel> getAllVendas() {
        return vendaRepository.findAll();
    }

    public Optional<VendaModel> getVendaById(Long id) {
        return vendaRepository.findById(id);
    }

    public VendaModel saveVenda(VendaModel venda) {
        return vendaRepository.save(venda);
    }

    public void deleteVenda(Long id) {
        vendaRepository.deleteById(id);
    }
}

