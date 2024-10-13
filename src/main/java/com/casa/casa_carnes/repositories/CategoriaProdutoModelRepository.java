package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.CategoriaProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProdutoModelRepository extends JpaRepository<CategoriaProdutoModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}

