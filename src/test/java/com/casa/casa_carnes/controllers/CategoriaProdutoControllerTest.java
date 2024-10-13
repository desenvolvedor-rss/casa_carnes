package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.models.CategoriaProdutoModel;
import com.casa.casa_carnes.repositories.CategoriaProdutoModelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriaProdutoController.class)
public class CategoriaProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaProdutoModelRepository categoriaProdutoModelRepository;

    @Test
    public void testGetCategorias() throws Exception {
        // Configura o comportamento esperado do mock
        given(categoriaProdutoModelRepository.findAll()).willReturn(List.of(new CategoriaProdutoModel()));

        // Realiza a requisição e verifica a resposta
        mockMvc.perform(get("/api/categorias-produto"))
                .andExpect(status().isOk());
    }
}
