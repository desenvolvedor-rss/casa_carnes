package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.ItemVendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaModelRepository extends JpaRepository<ItemVendaModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}


