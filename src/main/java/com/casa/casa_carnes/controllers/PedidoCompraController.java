package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.repositories.PedidoCompraModelRepository;
import com.casa.casa_carnes.models.PedidoCompraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos-compra")
public class PedidoCompraController {

    @Autowired
    private PedidoCompraModelRepository pedidoCompraRepository;

    @GetMapping
    public List<PedidoCompraModel> getAllPedidosCompra() {
        return pedidoCompraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoCompraModel> getPedidoCompraById(@PathVariable Long id) {
        Optional<PedidoCompraModel> pedidoCompra = pedidoCompraRepository.findById(id);
        return pedidoCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoCompraModel> createPedidoCompra(@RequestBody PedidoCompraModel pedidoCompra) {
        PedidoCompraModel savedPedidoCompra = pedidoCompraRepository.save(pedidoCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPedidoCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoCompraModel> updatePedidoCompra(@PathVariable Long id, @RequestBody PedidoCompraModel pedidoCompra) {
        if (!pedidoCompraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoCompra.setPedidoId(id);
        PedidoCompraModel updatedPedidoCompra = pedidoCompraRepository.save(pedidoCompra);
        return ResponseEntity.ok(updatedPedidoCompra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoCompra(@PathVariable Long id) {
        if (!pedidoCompraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoCompraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
