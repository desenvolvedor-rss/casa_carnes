package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.repositories.VendaModelRepository;
import com.casa.casa_carnes.models.VendaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaModelRepository vendaRepository;

    @GetMapping
    public List<VendaModel> getAllVendas() {
        return vendaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaModel> getVendaById(@PathVariable Long id) {
        Optional<VendaModel> venda = vendaRepository.findById(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VendaModel> createVenda(@RequestBody VendaModel venda) {
        VendaModel savedVenda = vendaRepository.save(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaModel> updateVenda(@PathVariable Long id, @RequestBody VendaModel venda) {
        if (!vendaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        venda.setVendaId(id);
        VendaModel updatedVenda = vendaRepository.save(venda);
        return ResponseEntity.ok(updatedVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
        if (!vendaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vendaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

