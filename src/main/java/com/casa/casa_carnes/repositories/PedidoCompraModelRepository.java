package com.casa.casa_carnes.repositories;

import com.casa.casa_carnes.models.PedidoCompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCompraModelRepository extends JpaRepository<PedidoCompraModel, Long> {
    // Adicione métodos personalizados de consulta, se necessário
}
