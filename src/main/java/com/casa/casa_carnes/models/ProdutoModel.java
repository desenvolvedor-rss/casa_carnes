package com.casa.casa_carnes.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "produtos")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Long produtoId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @Column(name = "unidade_medida")
    private String unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private FornecedorModel fornecedor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaProdutoModel categoriaProduto; // Corrigido aqui

    @OneToMany(mappedBy = "produto")
    private Set<ItemVendaModel> itensVenda;

    @OneToMany(mappedBy = "produto")
    private Set<ItemPedidoModel> itensPedido;

    public void setProdutoId(Long id) {
    }

    // Getters e Setters, se necessário
}
