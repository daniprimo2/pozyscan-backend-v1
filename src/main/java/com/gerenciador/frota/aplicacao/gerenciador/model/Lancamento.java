package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.frota.aplicacao.gerenciador.dto.StatusLancamento;
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
@Table(name = "tb_lancamento", schema = "sc_gerenciador")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lancamento")
    private Long id;

    @Column(name = "data_lancamento")
    private String data;

    @ManyToOne
    private Aplicacao aplicacao_id;

    @ManyToOne
    private Filial filial_id;

    @ManyToOne
    private Veiculo veiculo_id;

    @ManyToOne
    private Fornecedor fornecedor_id;

    @OneToOne
    private NotaFiscal numero_nf;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_lancamento")
    private StatusLancamento status;

    @Column(name = "centro_de_custo")
    private String centroCusto;

}
