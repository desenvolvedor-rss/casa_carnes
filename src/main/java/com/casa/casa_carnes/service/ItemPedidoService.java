package com.casa.casa_carnes.service;

import com.casa.casa_carnes.repositories.ItemPedidoModelRepository;
import com.casa.casa_carnes.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoModelRepository itemPedidoRepository;

    public List<ItemPedidoModel> getAllItensPedido() {
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedidoModel> getItemPedidoById(Long id) {
        return itemPedidoRepository.findById(id);
    }

    public ItemPedidoModel saveItemPedido(ItemPedidoModel itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public void deleteItemPedido(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}

