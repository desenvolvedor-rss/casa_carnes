package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.repositories.ItemPedidoModelRepository;
import com.casa.casa_carnes.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoModelRepository itemPedidoRepository;

    @GetMapping
    public List<ItemPedidoModel> getAllItensPedido() {
        return itemPedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoModel> getItemPedidoById(@PathVariable Long id) {
        Optional<ItemPedidoModel> itemPedido = itemPedidoRepository.findById(id);
        return itemPedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemPedidoModel> createItemPedido(@RequestBody ItemPedidoModel itemPedido) {
        ItemPedidoModel savedItemPedido = itemPedidoRepository.save(itemPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoModel> updateItemPedido(@PathVariable Long id, @RequestBody ItemPedidoModel itemPedido) {
        if (!itemPedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemPedido.setItemPedidoId(id);
        ItemPedidoModel updatedItemPedido = itemPedidoRepository.save(itemPedido);
        return ResponseEntity.ok(updatedItemPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemPedido(@PathVariable Long id) {
        if (!itemPedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemPedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

