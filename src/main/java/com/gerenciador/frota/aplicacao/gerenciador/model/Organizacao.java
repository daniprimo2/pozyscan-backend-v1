package com.gerenciador.frota.aplicacao.gerenciador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_organizacao", schema = "sc_organizacao")
public class Organizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organizacao")
    private Long id;

    @Column(name = "nome_organizacao")
    private String nome;

    @Column(name = "cnpj_organizacao")
    private String cnpj;

    @Embedded
    private Endereco endereco;



}
