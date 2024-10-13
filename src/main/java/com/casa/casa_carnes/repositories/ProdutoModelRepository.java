package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoModelRepository extends JpaRepository<ProdutoModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}
