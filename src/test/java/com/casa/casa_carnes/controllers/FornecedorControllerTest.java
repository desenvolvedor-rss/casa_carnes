package com.casa.casa_carnes.controllers;


import com.casa.casa_carnes.repositories.FornecedorModelRepository;
import com.casa.casa_carnes.models.FornecedorModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FornecedorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FornecedorModelRepository fornecedorRepository;

    @InjectMocks
    private FornecedorController fornecedorController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(fornecedorController).build();
    }

    @Test
    public void testGetAllFornecedores() throws Exception {
        // Arrange
        FornecedorModel fornecedor1 = new FornecedorModel();
        fornecedor1.setFornecedorId(1L);
        fornecedor1.setNome("Fornecedor 1");

        FornecedorModel fornecedor2 = new FornecedorModel();
        fornecedor2.setFornecedorId(2L);
        fornecedor2.setNome("Fornecedor 2");

        List<FornecedorModel> fornecedores = Arrays.asList(fornecedor1, fornecedor2);

        when(fornecedorRepository.findAll()).thenReturn(fornecedores);

        // Act & Assert
        mockMvc.perform(get("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"fornecedorId\": 1, \"nome\": \"Fornecedor 1\"}, {\"fornecedorId\": 2, \"nome\": \"Fornecedor 2\"}]"));

    }
}

