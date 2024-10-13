package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.models.ItemVendaModel;
import com.casa.casa_carnes.repositories.ItemVendaModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemVendaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemVendaModelRepository itemVendaRepository;

    @InjectMocks
    private ItemVendaController itemVendaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemVendaController).build();
    }

    @Test
    void testGetAllItensVenda() throws Exception {
        when(itemVendaRepository.findAll()).thenReturn(Collections.singletonList(new ItemVendaModel()));

        mockMvc.perform(get("/api/itens-venda"))
                .andExpect(status().isOk());

        verify(itemVendaRepository, times(1)).findAll();
    }

    @Test
    void testGetItemVendaById() throws Exception {
        ItemVendaModel itemVenda = new ItemVendaModel();
        itemVenda.setItemId(1L);

        when(itemVendaRepository.findById(anyLong())).thenReturn(Optional.of(itemVenda));

        mockMvc.perform(get("/api/itens-venda/1"))
                .andExpect(status().isOk());

        verify(itemVendaRepository, times(1)).findById(1L);
    }

    @Test
    void testGetItemVendaByIdNotFound() throws Exception {
        when(itemVendaRepository.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/itens-venda/1"))
                .andExpect(status().isNotFound());

        verify(itemVendaRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateItemVenda() throws Exception {
        ItemVendaModel itemVenda = new ItemVendaModel();
        itemVenda.setItemId(1L);
        itemVenda.setQuantidade(10);
        itemVenda.setPrecoVendido(new BigDecimal("100.00"));

        when(itemVendaRepository.save(any(ItemVendaModel.class))).thenReturn(itemVenda);

        mockMvc.perform(post("/api/itens-venda")
                        .contentType("application/json")
                        .content("{ \"quantidade\": 10, \"precoVendido\": \"100.00\" }"))
                .andExpect(status().isCreated());

        verify(itemVendaRepository, times(1)).save(any(ItemVendaModel.class));
    }

    @Test
    void testUpdateItemVenda() throws Exception {
        ItemVendaModel existingItemVenda = new ItemVendaModel();
        existingItemVenda.setItemId(1L);
        existingItemVenda.setQuantidade(10);
        existingItemVenda.setPrecoVendido(new BigDecimal("100.00"));

        when(itemVendaRepository.existsById(anyLong())).thenReturn(true);
        when(itemVendaRepository.save(any(ItemVendaModel.class))).thenReturn(existingItemVenda);

        mockMvc.perform(put("/api/itens-venda/1")
                        .contentType("application/json")
                        .content("{ \"quantidade\": 20, \"precoVendido\": \"150.00\" }"))
                .andExpect(status().isOk());

        verify(itemVendaRepository, times(1)).save(any(ItemVendaModel.class));
    }

    @Test
    void testUpdateItemVendaNotFound() throws Exception {
        when(itemVendaRepository.existsById(anyLong())).thenReturn(false);

        mockMvc.perform(put("/api/itens-venda/1")
                        .contentType("application/json")
                        .content("{ \"quantidade\": 20, \"precoVendido\": \"150.00\" }"))
                .andExpect(status().isNotFound());

        verify(itemVendaRepository, times(1)).existsById(1L);
        verify(itemVendaRepository, never()).save(any(ItemVendaModel.class));
    }

    @Test
    void testDeleteItemVenda() throws Exception {
        when(itemVendaRepository.existsById(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/itens-venda/1"))
                .andExpect(status().isNoContent());

        verify(itemVendaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteItemVendaNotFound() throws Exception {
        when(itemVendaRepository.existsById(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/api/itens-venda/1"))
                .andExpect(status().isNotFound());

        verify(itemVendaRepository, times(1)).existsById(1L);
        verify(itemVendaRepository, never()).deleteById(1L);
    }
}
