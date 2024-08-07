package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tb_filial", schema = "sc_gerenciador")
public class Filial {

    @Id
    @Column(name = "id_nome_filial", nullable = false)
    private String nome;

    @Column(name = "centro_de_custo")
    private String centroDeCusto;

    @JsonIgnore
    @OneToMany(mappedBy = "filial_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lancamento> lancamentos = new ArrayList<>();

    @ManyToOne
    private Organizacao organizacao;

    private String patente;


}
