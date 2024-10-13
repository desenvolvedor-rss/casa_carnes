package com.casa.casa_carnes.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_venda")
public class ItemVendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private VendaModel venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_vendido")
    private BigDecimal precoVendido;

    public void setItemId(Long id) {
    }

    public void setQuantidade(int i) {
    }

    public void setPrecoVendido(BigDecimal bigDecimal) {
    }

    // Getters e Setters
}

