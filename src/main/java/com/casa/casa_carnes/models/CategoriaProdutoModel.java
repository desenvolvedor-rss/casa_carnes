package com.casa.casa_carnes.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "categorias_produto")
public class CategoriaProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long categoriaId;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "categoriaProduto")
    private Set<ProdutoModel> produtos;

    public void setCategoriaId(Long id) {
    }
}
