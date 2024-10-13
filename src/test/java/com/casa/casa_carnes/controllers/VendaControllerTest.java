package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.models.VendaModel;
import com.casa.casa_carnes.repositories.VendaModelRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VendaControllerTest {

    @InjectMocks
    private VendaController vendaController;

    @Mock
    private VendaModelRepository vendaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVendas() {
        // Arrange
        VendaModel venda1 = new VendaModel();
        venda1.setVendaId(1L);
        venda1.setDataVenda(LocalDate.now());
        venda1.setValorTotal(BigDecimal.valueOf(100.00));

        VendaModel venda2 = new VendaModel();
        venda2.setVendaId(2L);
        venda2.setDataVenda(LocalDate.now());
        venda2.setValorTotal(BigDecimal.valueOf(200.00));

        List<VendaModel> vendas = Arrays.asList(venda1, venda2);
        when(vendaRepository.findAll()).thenReturn(vendas);

        // Act
        List<VendaModel> result = vendaController.getAllVendas();

        // Assert
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getVendaId());
        assertEquals(2L, result.get(1).getVendaId());
    }

    @Test
    void testGetVendaById_Success() {
        // Arrange
        VendaModel venda = new VendaModel();
        venda.setVendaId(1L);
        venda.setDataVenda(LocalDate.now());
        venda.setValorTotal(BigDecimal.valueOf(100.00));

        when(vendaRepository.findById(1L)).thenReturn(Optional.of(venda));

        // Act
        ResponseEntity<VendaModel> result = vendaController.getVendaById(1L);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1L, result.getBody().getVendaId());
    }

    @Test
    void testGetVendaById_NotFound() {
        // Arrange
        when(vendaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<VendaModel> result = vendaController.getVendaById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void testCreateVenda() {
        // Arrange
        VendaModel venda = new VendaModel();
        venda.setDataVenda(LocalDate.now());
        venda.setValorTotal(BigDecimal.valueOf(100.00));

        VendaModel savedVenda = new VendaModel();
        savedVenda.setVendaId(1L);
        savedVenda.setDataVenda(LocalDate.now());
        savedVenda.setValorTotal(BigDecimal.valueOf(100.00));

        when(vendaRepository.save(any(VendaModel.class))).thenReturn(savedVenda);

        // Act
        ResponseEntity<VendaModel> result = vendaController.createVenda(venda);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(1L, result.getBody().getVendaId());
    }

    @Test
    void testUpdateVenda_Success() {
        // Arrange
        VendaModel venda = new VendaModel();
        venda.setVendaId(1L);
        venda.setDataVenda(LocalDate.now());
        venda.setValorTotal(BigDecimal.valueOf(100.00));

        when(vendaRepository.existsById(1L)).thenReturn(true);
        when(vendaRepository.save(any(VendaModel.class))).thenReturn(venda);

        // Act
        ResponseEntity<VendaModel> result = vendaController.updateVenda(1L, venda);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1L, result.getBody().getVendaId());
    }

    @Test
    void testUpdateVenda_NotFound() {
        // Arrange
        when(vendaRepository.existsById(anyLong())).thenReturn(false);

        // Act
        ResponseEntity<VendaModel> result = vendaController.updateVenda(1L, new VendaModel());

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void testDeleteVenda_Success() {
        // Arrange
        when(vendaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(vendaRepository).deleteById(1L);

        // Act
        ResponseEntity<Void> result = vendaController.deleteVenda(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testDeleteVenda_NotFound() {
        // Arrange
        when(vendaRepository.existsById(anyLong())).thenReturn(false);

        // Act
        ResponseEntity<Void> result = vendaController.deleteVenda(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
