package com.casa.casa_carnes.service;

import com.casa.casa_carnes.repositories.ItemVendaModelRepository;
import com.casa.casa_carnes.models.ItemVendaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaModelRepository itemVendaRepository;

    public List<ItemVendaModel> getAllItensVenda() {
        return itemVendaRepository.findAll();
    }

    public Optional<ItemVendaModel> getItemVendaById(Long id) {
        return itemVendaRepository.findById(id);
    }

    public ItemVendaModel saveItemVenda(ItemVendaModel itemVenda) {
        return itemVendaRepository.save(itemVenda);
    }

    public void deleteItemVenda(Long id) {
        itemVendaRepository.deleteById(id);
    }
}

