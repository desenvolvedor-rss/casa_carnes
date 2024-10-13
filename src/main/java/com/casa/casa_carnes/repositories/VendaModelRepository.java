package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaModelRepository extends JpaRepository<VendaModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}

