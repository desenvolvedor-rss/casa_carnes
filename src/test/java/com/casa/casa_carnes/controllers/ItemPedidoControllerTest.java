package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.models.ItemPedidoModel;
import com.casa.casa_carnes.repositories.ItemPedidoModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ItemPedidoControllerTest {

    @Mock
    private ItemPedidoModelRepository itemPedidoRepository;

    @InjectMocks
    private ItemPedidoController itemPedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItensPedido() {
        List<ItemPedidoModel> itemList = new ArrayList<>();
        itemList.add(new ItemPedidoModel());
        when(itemPedidoRepository.findAll()).thenReturn(itemList);

        List<ItemPedidoModel> result = itemPedidoController.getAllItensPedido();
        assertEquals(1, result.size());
        verify(itemPedidoRepository, times(1)).findAll();
    }

    @Test
    void testGetItemPedidoById() {
        ItemPedidoModel itemPedido = new ItemPedidoModel();
        when(itemPedidoRepository.findById(anyLong())).thenReturn(Optional.of(itemPedido));

        ResponseEntity<ItemPedidoModel> response = itemPedidoController.getItemPedidoById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(itemPedidoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetItemPedidoById_NotFound() {
        when(itemPedidoRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<ItemPedidoModel> response = itemPedidoController.getItemPedidoById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(itemPedidoRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateItemPedido() {
        ItemPedidoModel itemPedido = new ItemPedidoModel();
        when(itemPedidoRepository.save(any(ItemPedidoModel.class))).thenReturn(itemPedido);

        ResponseEntity<ItemPedidoModel> response = itemPedidoController.createItemPedido(itemPedido);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(itemPedidoRepository, times(1)).save(itemPedido);
    }

    @Test
    void testUpdateItemPedido() {
        ItemPedidoModel itemPedido = new ItemPedidoModel();
        when(itemPedidoRepository.existsById(anyLong())).thenReturn(true);
        when(itemPedidoRepository.save(any(ItemPedidoModel.class))).thenReturn(itemPedido);

        ResponseEntity<ItemPedidoModel> response = itemPedidoController.updateItemPedido(1L, itemPedido);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(itemPedidoRepository, times(1)).existsById(1L);
        verify(itemPedidoRepository, times(1)).save(itemPedido);
    }

    @Test
    void testUpdateItemPedido_NotFound() {
        when(itemPedidoRepository.existsById(anyLong())).thenReturn(false);

        ResponseEntity<ItemPedidoModel> response = itemPedidoController.updateItemPedido(1L, new ItemPedidoModel());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(itemPedidoRepository, times(1)).existsById(1L);
        verify(itemPedidoRepository, times(0)).save(any(ItemPedidoModel.class));
    }

    @Test
    void testDeleteItemPedido() {
        when(itemPedidoRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(itemPedidoRepository).deleteById(anyLong());

        ResponseEntity<Void> response = itemPedidoController.deleteItemPedido(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(itemPedidoRepository, times(1)).existsById(1L);
        verify(itemPedidoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteItemPedido_NotFound() {
        when(itemPedidoRepository.existsById(anyLong())).thenReturn(false);

        ResponseEntity<Void> response = itemPedidoController.deleteItemPedido(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(itemPedidoRepository, times(1)).existsById(1L);
        verify(itemPedidoRepository, times(0)).deleteById(anyLong());
    }
}
