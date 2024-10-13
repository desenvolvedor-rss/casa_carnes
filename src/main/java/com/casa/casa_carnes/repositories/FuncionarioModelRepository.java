package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioModelRepository extends JpaRepository<FuncionarioModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}
