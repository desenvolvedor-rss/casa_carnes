package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.models.CategoriaProdutoModel;
import com.casa.casa_carnes.repositories.CategoriaProdutoModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias-produto")
public class CategoriaProdutoController {

    @Autowired
    private CategoriaProdutoModelRepository categoriaProdutoRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoModel>> getAllCategoriasProduto() {
        List<CategoriaProdutoModel> categorias = categoriaProdutoRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoModel> getCategoriaProdutoById(@PathVariable Long id) {
        Optional<CategoriaProdutoModel> categoriaProduto = categoriaProdutoRepository.findById(id);
        return categoriaProduto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaProdutoModel> createCategoriaProduto(@RequestBody CategoriaProdutoModel categoriaProduto) {
        CategoriaProdutoModel savedCategoriaProduto = categoriaProdutoRepository.save(categoriaProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoriaProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProdutoModel> updateCategoriaProduto(@PathVariable Long id, @RequestBody CategoriaProdutoModel categoriaProduto) {
        if (!categoriaProdutoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaProduto.setCategoriaId(id);
        CategoriaProdutoModel updatedCategoriaProduto = categoriaProdutoRepository.save(categoriaProduto);
        return ResponseEntity.ok(updatedCategoriaProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaProduto(@PathVariable Long id) {
        if (!categoriaProdutoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaProdutoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
