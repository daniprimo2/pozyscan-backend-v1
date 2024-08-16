package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.FilialResponse;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectFilialResponse;
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
@Table(name = "tb_filial", schema = "sc_gerenciador")
public class Filial {

    @Id
    @Column(name = "id_filial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome_filial")
    private String nome;

    @Column(name = "centro_de_custo")
    private String centroDeCusto;

    @JsonIgnore
    @OneToMany(mappedBy = "filial_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lancamento> lancamentos = new ArrayList<>();

    @ManyToOne
    private Organizacao organizacao;

    private String patente;


    public FilialResponse construirResponse() {
        return FilialResponse.builder()
                .id(id)
                .nome(nome)
                .centroDeCusto(centroDeCusto)
                .patente(patente)
                .build();
    }

    public SelectFilialResponse tratarSelectsFiliais() {
        return SelectFilialResponse.builder()
                .idFilial(this.id)
                .nomeFilial(this.nome)
                .build();
    }
}
