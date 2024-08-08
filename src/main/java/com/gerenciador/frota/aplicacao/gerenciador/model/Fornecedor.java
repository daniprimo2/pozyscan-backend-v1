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
@Table(name = "tb_fornecedor", schema = "sc_gerenciador")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Long id;

    @Column(name = "nome_fornecedor")
    private String nome;

    @Column(name = "cnpj_fornecedor")
    private String cnpj;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_filial")
    private Filial filial;

}
