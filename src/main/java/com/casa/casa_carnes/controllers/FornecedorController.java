package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.repositories.FornecedorModelRepository;
import com.casa.casa_carnes.models.FornecedorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorModelRepository fornecedorRepository;

    @GetMapping
    public List<FornecedorModel> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorModel> getFornecedorById(@PathVariable Long id) {
        Optional<FornecedorModel> fornecedor = fornecedorRepository.findById(id);
        return fornecedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FornecedorModel> createFornecedor(@RequestBody FornecedorModel fornecedor) {
        FornecedorModel savedFornecedor = fornecedorRepository.save(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorModel> updateFornecedor(@PathVariable Long id, @RequestBody FornecedorModel fornecedor) {
        if (!fornecedorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fornecedor.setFornecedorId(id);
        FornecedorModel updatedFornecedor = fornecedorRepository.save(fornecedor);
        return ResponseEntity.ok(updatedFornecedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        if (!fornecedorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fornecedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

