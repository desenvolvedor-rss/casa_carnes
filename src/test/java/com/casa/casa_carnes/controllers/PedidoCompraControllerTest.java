package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.models.PedidoCompraModel;
import com.casa.casa_carnes.repositories.PedidoCompraModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoCompraControllerTest {

    @InjectMocks
    private PedidoCompraController pedidoCompraController;

    @Mock
    private PedidoCompraModelRepository pedidoCompraRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPedidosCompra() {
        PedidoCompraModel pedido1 = new PedidoCompraModel();
        PedidoCompraModel pedido2 = new PedidoCompraModel();
        when(pedidoCompraRepository.findAll()).thenReturn(Arrays.asList(pedido1, pedido2));

        List<PedidoCompraModel> pedidos = pedidoCompraController.getAllPedidosCompra();

        assertEquals(2, pedidos.size());
        verify(pedidoCompraRepository, times(1)).findAll();
    }

    @Test
    public void testGetPedidoCompraById_Found() {
        PedidoCompraModel pedido = new PedidoCompraModel();
        when(pedidoCompraRepository.findById(1L)).thenReturn(Optional.of(pedido));

        ResponseEntity<PedidoCompraModel> response = pedidoCompraController.getPedidoCompraById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedido, response.getBody());
        verify(pedidoCompraRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPedidoCompraById_NotFound() {
        when(pedidoCompraRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<PedidoCompraModel> response = pedidoCompraController.getPedidoCompraById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(pedidoCompraRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreatePedidoCompra() {
        PedidoCompraModel pedido = new PedidoCompraModel();
        when(pedidoCompraRepository.save(any(PedidoCompraModel.class))).thenReturn(pedido);

        ResponseEntity<PedidoCompraModel> response = pedidoCompraController.createPedidoCompra(pedido);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pedido, response.getBody());
        verify(pedidoCompraRepository, times(1)).save(pedido);
    }

    @Test
    public void testUpdatePedidoCompra_Found() {
        PedidoCompraModel pedido = new PedidoCompraModel();
        when(pedidoCompraRepository.existsById(1L)).thenReturn(true);
        when(pedidoCompraRepository.save(any(PedidoCompraModel.class))).thenReturn(pedido);

        ResponseEntity<PedidoCompraModel> response = pedidoCompraController.updatePedidoCompra(1L, pedido);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedido, response.getBody());
        verify(pedidoCompraRepository, times(1)).existsById(1L);
        verify(pedidoCompraRepository, times(1)).save(pedido);
    }

    @Test
    public void testUpdatePedidoCompra_NotFound() {
        PedidoCompraModel pedido = new PedidoCompraModel();
        when(pedidoCompraRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<PedidoCompraModel> response = pedidoCompraController.updatePedidoCompra(1L, pedido);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(pedidoCompraRepository, times(1)).existsById(1L);
        verify(pedidoCompraRepository, never()).save(pedido);
    }

    @Test
    public void testDeletePedidoCompra_Found() {
        when(pedidoCompraRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = pedidoCompraController.deletePedidoCompra(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pedidoCompraRepository, times(1)).existsById(1L);
        verify(pedidoCompraRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletePedidoCompra_NotFound() {
        when(pedidoCompraRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> response = pedidoCompraController.deletePedidoCompra(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(pedidoCompraRepository, times(1)).existsById(1L);
        verify(pedidoCompraRepository, never()).deleteById(1L);
    }
}

