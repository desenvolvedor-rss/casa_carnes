package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoModelRepository extends JpaRepository<ItemPedidoModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}

