package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.repositories.ItemVendaModelRepository;
import com.casa.casa_carnes.models.ItemVendaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens-venda")
public class ItemVendaController {

    @Autowired
    private ItemVendaModelRepository itemVendaRepository;

    @GetMapping
    public List<ItemVendaModel> getAllItensVenda() {
        return itemVendaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemVendaModel> getItemVendaById(@PathVariable Long id) {
        Optional<ItemVendaModel> itemVenda = itemVendaRepository.findById(id);
        return itemVenda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemVendaModel> createItemVenda(@RequestBody ItemVendaModel itemVenda) {
        ItemVendaModel savedItemVenda = itemVendaRepository.save(itemVenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVendaModel> updateItemVenda(@PathVariable Long id, @RequestBody ItemVendaModel itemVenda) {
        if (!itemVendaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemVenda.setItemId(id);
        ItemVendaModel updatedItemVenda = itemVendaRepository.save(itemVenda);
        return ResponseEntity.ok(updatedItemVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemVenda(@PathVariable Long id) {
        if (!itemVendaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemVendaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


