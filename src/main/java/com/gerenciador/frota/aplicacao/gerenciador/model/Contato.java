package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "tb_contato", schema = "sc_gerenciador")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato")
    private Long id;

    @Column(name = "nome_contato")
    private String nome;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fornecedor_id") // Nome da coluna correspondente na tabela
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EmailContato> emails = new ArrayList<>();

    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<>();



}
