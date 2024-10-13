package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteModelRepository extends JpaRepository<ClienteModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}

