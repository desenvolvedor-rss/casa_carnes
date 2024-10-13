package com.casa.casa_carnes.service;

import com.casa.casa_carnes.repositories.CategoriaProdutoModelRepository;
import com.casa.casa_carnes.models.CategoriaProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoModelRepository categoriaProdutoRepository;

    public List<CategoriaProdutoModel> getAllCategoriasProduto() {
        return categoriaProdutoRepository.findAll();
    }

    public Optional<CategoriaProdutoModel> getCategoriaProdutoById(Long id) {
        return categoriaProdutoRepository.findById(id);
    }

    public CategoriaProdutoModel saveCategoriaProduto(CategoriaProdutoModel categoriaProduto) {
        return categoriaProdutoRepository.save(categoriaProduto);
    }

    public void deleteCategoriaProduto(Long id) {
        categoriaProdutoRepository.deleteById(id);
    }
}

