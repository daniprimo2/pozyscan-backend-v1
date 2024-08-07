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
    private Fornecedor fornecedor_id;

    @OneToMany(mappedBy = "contato_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmailContato> emails = new ArrayList<>();

    @OneToMany(mappedBy = "contato_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefone> telefones = new ArrayList<>();



}
