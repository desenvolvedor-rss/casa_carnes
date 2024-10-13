package com.casa.casa_carnes.service;

import com.casa.casa_carnes.repositories.PedidoCompraModelRepository;
import com.casa.casa_carnes.models.PedidoCompraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoCompraService {

    @Autowired
    private PedidoCompraModelRepository pedidoCompraRepository;

    public List<PedidoCompraModel> getAllPedidosCompra() {
        return pedidoCompraRepository.findAll();
    }

    public Optional<PedidoCompraModel> getPedidoCompraById(Long id) {
        return pedidoCompraRepository.findById(id);
    }

    public PedidoCompraModel savePedidoCompra(PedidoCompraModel pedidoCompra) {
        return pedidoCompraRepository.save(pedidoCompra);
    }

    public void deletePedidoCompra(Long id) {
        pedidoCompraRepository.deleteById(id);
    }
}

