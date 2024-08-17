package com.gerenciador.frota.aplicacao.gerenciador.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tipo", schema = "sc_gerenciador")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long id;

    @Column(name = "nome_tipo")
    private String nome;

    @Column(name = "descricao_tipo")
    private String descricao;

    @ManyToOne
    private Filial filial;

}
