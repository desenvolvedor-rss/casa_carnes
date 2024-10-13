package com.casa.casa_carnes.service;

import com.casa.casa_carnes.repositories.FuncionarioModelRepository;
import com.casa.casa_carnes.models.FuncionarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioModelRepository funcionarioRepository;

    public List<FuncionarioModel> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioModel> getFuncionarioById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public FuncionarioModel saveFuncionario(FuncionarioModel funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }
}

