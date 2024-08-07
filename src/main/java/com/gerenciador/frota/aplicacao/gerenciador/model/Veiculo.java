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
@Table(name = "tb_veiculo", schema = "sc_gerenciador")
public class Veiculo {

    @Id
    @Column(name = "placa_veiculo")
    private String placa;

    @Column(name = "modelo_veiculo")
    private String modelo;

    @Column(name = "ano_veiculo")
    private String ano;

    @ManyToOne
    private Categoria categoria_id;

    @ManyToOne
    private Tipo tipo_id;

    @OneToMany(mappedBy = "veiculo_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos = new ArrayList<>();

    @ManyToOne
    private Organizacao organizacao;

    @ManyToOne
    private Filial filial;

}
