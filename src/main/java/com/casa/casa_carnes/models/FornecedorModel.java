package com.casa.casa_carnes.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fornecedores")
public class FornecedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fornecedor_id")
    private Long fornecedorId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "endereco")
    private String endereco;

    @OneToMany(mappedBy = "fornecedor")
    private Set<ProdutoModel> produtos;

    @OneToMany(mappedBy = "fornecedor")
    private Set<PedidoCompraModel> pedidosCompra;

    // Getters e Setters

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    public Set<PedidoCompraModel> getPedidosCompra() {
        return pedidosCompra;
    }

    public void setPedidosCompra(Set<PedidoCompraModel> pedidosCompra) {
        this.pedidosCompra = pedidosCompra;
    }
}