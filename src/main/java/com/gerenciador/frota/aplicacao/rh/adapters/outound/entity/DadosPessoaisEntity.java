package com.gerenciador.frota.aplicacao.rh.adapters.outound.entity;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_DADOS_PESSOAIS", schema = "sc_recursos_humanos")
public class DadosPessoaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_DADOS_PESSOAIS")
    private Long id;
    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;
    @Column(name = "DATA_NASCIMENTO")
    private String dataNascimento;
    @Column(name = "NOME_MAE")
    private String nomeMae;
    @Column(name = "NOME_PAI")
    private String nomePai;

    @ManyToOne
    @JoinColumn(name = "COD_ENDERECO")
    private Endereco endereco;




}
