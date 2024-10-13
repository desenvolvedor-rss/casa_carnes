package com.casa.casa_carnes.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_pedido_id")
    private Long itemPedidoId;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoCompraModel pedidoCompra;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_compra")
    private BigDecimal precoCompra;

    public void setItemPedidoId(Long id) {
    }

    // Getters e Setters
}

