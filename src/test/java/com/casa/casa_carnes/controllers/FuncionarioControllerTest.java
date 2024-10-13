package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.models.FuncionarioModel;
import com.casa.casa_carnes.repositories.FuncionarioModelRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FuncionarioControllerTest {

    @Mock
    private FuncionarioModelRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFuncionarios() {
        FuncionarioModel funcionario1 = new FuncionarioModel();
        funcionario1.setNome("João");
        funcionario1.setCpf("12345678900");
        funcionario1.setCargo("Vendedor");
        funcionario1.setSalario(new BigDecimal("1500.00"));

        FuncionarioModel funcionario2 = new FuncionarioModel();
        funcionario2.setNome("Maria");
        funcionario2.setCpf("09876543211");
        funcionario2.setCargo("Caixa");
        funcionario2.setSalario(new BigDecimal("1200.00"));

        List<FuncionarioModel> funcionarios = Arrays.asList(funcionario1, funcionario2);

        when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        List<FuncionarioModel> result = funcionarioController.getAllFuncionarios();
        assertEquals(2, result.size());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    void testGetFuncionarioById() {
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo("Vendedor");
        funcionario.setSalario(new BigDecimal("1500.00"));

        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionario));

        ResponseEntity<FuncionarioModel> response = funcionarioController.getFuncionarioById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionario, response.getBody());
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFuncionarioByIdNotFound() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<FuncionarioModel> response = funcionarioController.getFuncionarioById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateFuncionario() {
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo("Vendedor");
        funcionario.setSalario(new BigDecimal("1500.00"));

        when(funcionarioRepository.save(any(FuncionarioModel.class))).thenReturn(funcionario);

        ResponseEntity<FuncionarioModel> response = funcionarioController.createFuncionario(funcionario);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(funcionario, response.getBody());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void testUpdateFuncionario() {
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo("Vendedor");
        funcionario.setSalario(new BigDecimal("1500.00"));

        when(funcionarioRepository.existsById(anyLong())).thenReturn(true);
        when(funcionarioRepository.save(any(FuncionarioModel.class))).thenReturn(funcionario);

        ResponseEntity<FuncionarioModel> response = funcionarioController.updateFuncionario(1L, funcionario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionario, response.getBody());
        verify(funcionarioRepository, times(1)).existsById(1L);
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void testUpdateFuncionarioNotFound() {
        when(funcionarioRepository.existsById(anyLong())).thenReturn(false);

        FuncionarioModel funcionario = new FuncionarioModel();
        ResponseEntity<FuncionarioModel> response = funcionarioController.updateFuncionario(1L, funcionario);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(funcionarioRepository, times(1)).existsById(1L);
    }

    @Test
    void testDeleteFuncionario() {
        when(funcionarioRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(funcionarioRepository).deleteById(anyLong());

        ResponseEntity<Void> response = funcionarioController.deleteFuncionario(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(funcionarioRepository, times(1)).existsById(1L);
        verify(funcionarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteFuncionarioNotFound() {
        when(funcionarioRepository.existsById(anyLong())).thenReturn(false);

        ResponseEntity<Void> response = funcionarioController.deleteFuncionario(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(funcionarioRepository, times(1)).existsById(1L);
    }
}
