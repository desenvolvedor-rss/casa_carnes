package com.casa.casa_carnes.service;

import com.casa.casa_carnes.repositories.FornecedorModelRepository;
import com.casa.casa_carnes.models.FornecedorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorModelRepository fornecedorRepository;

    public List<FornecedorModel> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Optional<FornecedorModel> getFornecedorById(Long id) {
        return fornecedorRepository.findById(id);
    }

    public FornecedorModel saveFornecedor(FornecedorModel fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deleteFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
}

