package com.casa.casa_carnes.service;

import com.casa.casa_carnes.repositories.ProdutoModelRepository;
import com.casa.casa_carnes.models.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoModelRepository produtoRepository;

    public List<ProdutoModel> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> getProdutoById(Long id) {
        return produtoRepository.findById(id);
    }

    public ProdutoModel saveProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
