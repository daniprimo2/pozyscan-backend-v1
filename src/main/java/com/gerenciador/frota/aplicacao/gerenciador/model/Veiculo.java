package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectPlacaResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_veiculo", schema = "sc_gerenciador")
public class Veiculo {

    @Id
    @Column(name = "placa_veiculo")
    private String placa;

    @Column(name = "modelo_veiculo")
    private String modelo;

    @Column(name = "ano_veiculo")
    private String ano;

    @JsonIgnore
    @ManyToOne
    private Categoria categoria_id;

    @JsonIgnore
    @ManyToOne
    private Tipo tipo_id;

    @OneToMany(mappedBy = "veiculo_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos = new ArrayList<>();

    @ManyToOne
    private Organizacao organizacao;

    @ManyToOne
    private Filial filial;


    public SelectPlacaResponse tratandoSelectsPlaca(){
        return SelectPlacaResponse.builder()
                .placa(this.placa)
                .build();
    }

}
