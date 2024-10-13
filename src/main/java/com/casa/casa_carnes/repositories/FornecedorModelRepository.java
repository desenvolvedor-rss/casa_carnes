package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.FornecedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorModelRepository extends JpaRepository<FornecedorModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}
