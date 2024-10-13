package com.casa.casa_carnes.controllers;

import com.casa.casa_carnes.repositories.ClienteModelRepository;
import com.casa.casa_carnes.models.ClienteModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteModelRepository clienteRepository;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void testGetAllClientes() throws Exception {
        ClienteModel cliente1 = new ClienteModel();
        cliente1.setNome("Cliente 1");
        ClienteModel cliente2 = new ClienteModel();
        cliente2.setNome("Cliente 2");
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Cliente 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Cliente 2"))
                .andDo(print());

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testGetClienteById() throws Exception {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome("Cliente 1");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Cliente 1"))
                .andDo(print());

        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetClienteByIdNotFound() throws Exception {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());

        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCliente() throws Exception {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome("Cliente 1");
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(cliente);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .contentType("application/json")
                        .content("{\"nome\":\"Cliente 1\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Cliente 1"))
                .andDo(print());

        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome("Cliente 1");
        when(clienteRepository.existsById(1L)).thenReturn(true);
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(cliente);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/clientes/1")
                        .contentType("application/json")
                        .content("{\"nome\":\"Cliente 1\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Cliente 1"))
                .andDo(print());

        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
    }

    @Test
    public void testUpdateClienteNotFound() throws Exception {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/clientes/1")
                        .contentType("application/json")
                        .content("{\"nome\":\"Cliente 1\"}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());

        verify(clienteRepository, times(0)).save(any(ClienteModel.class));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clientes/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());

        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteClienteNotFound() throws Exception {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clientes/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());

        verify(clienteRepository, times(0)).deleteById(1L);
    }
}
