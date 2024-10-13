package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.models.ProdutoModel;
import com.casa.casa_carnes.repositories.ProdutoModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @Mock
    private ProdutoModelRepository produtoRepository;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProdutos() {
        // Arrange
        ProdutoModel produto1 = new ProdutoModel();
        ProdutoModel produto2 = new ProdutoModel();
        List<ProdutoModel> produtos = Arrays.asList(produto1, produto2);
        when(produtoRepository.findAll()).thenReturn(produtos);

        // Act
        List<ProdutoModel> result = produtoController.getAllProdutos();

        // Assert
        assertEquals(2, result.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void testGetProdutoById_Found() {
        // Arrange
        ProdutoModel produto = new ProdutoModel();
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        // Act
        ResponseEntity<ProdutoModel> response = produtoController.getProdutoById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produto, response.getBody());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProdutoById_NotFound() {
        // Arrange
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ProdutoModel> response = produtoController.getProdutoById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateProduto() {
        // Arrange
        ProdutoModel produto = new ProdutoModel();
        when(produtoRepository.save(any(ProdutoModel.class))).thenReturn(produto);

        // Act
        ResponseEntity<ProdutoModel> response = produtoController.createProduto(produto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(produto, response.getBody());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testUpdateProduto_Found() {
        // Arrange
        ProdutoModel produto = new ProdutoModel();
        when(produtoRepository.existsById(1L)).thenReturn(true);
        when(produtoRepository.save(any(ProdutoModel.class))).thenReturn(produto);

        // Act
        ResponseEntity<ProdutoModel> response = produtoController.updateProduto(1L, produto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produto, response.getBody());
        verify(produtoRepository, times(1)).existsById(1L);
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testUpdateProduto_NotFound() {
        // Arrange
        when(produtoRepository.existsById(1L)).thenReturn(false);

        // Act
        ResponseEntity<ProdutoModel> response = produtoController.updateProduto(1L, new ProdutoModel());

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(produtoRepository, times(1)).existsById(1L);
        verify(produtoRepository, times(0)).save(any(ProdutoModel.class));
    }

    @Test
    void testDeleteProduto_Found() {
        // Arrange
        when(produtoRepository.existsById(1L)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = produtoController.deleteProduto(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(produtoRepository, times(1)).existsById(1L);
        verify(produtoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProduto_NotFound() {
        // Arrange
        when(produtoRepository.existsById(1L)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = produtoController.deleteProduto(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(produtoRepository, times(1)).existsById(1L);
        verify(produtoRepository, times(0)).deleteById(anyLong());
    }
}
