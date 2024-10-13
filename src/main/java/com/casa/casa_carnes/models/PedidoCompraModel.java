package com.casa.casa_carnes.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "pedidos_compra")
public class PedidoCompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private FornecedorModel fornecedor;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "data_recebimento")
    private LocalDate dataRecebimento;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedidoCompra")
    private Set<ItemPedidoModel> itensPedido;

    public void setPedidoId(Long id) {
    }

    // Getters e Setters
}
