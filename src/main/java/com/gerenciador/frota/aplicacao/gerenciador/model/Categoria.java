package com.gerenciador.frota.aplicacao.gerenciador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_categoria", schema = "sc_gerenciador")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;

    @Column(name = "nome_categoria")
    private String nome;

    @Column(name = "descricao_categoria")
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoria_id")
    private List<Veiculo> veiculos = new ArrayList<>();

    @ManyToOne
    private Filial filial;

}
