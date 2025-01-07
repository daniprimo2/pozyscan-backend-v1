package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorResponse {

    private Long codigoColaborador;
    private String descricaoAtividade;
    private StatusColaborador status;
    private String dataContratacao;
    private String dataDemissao;
    private CargoEntity cargoEntity;
    private DadosPessoaisEntity dadosPessoaisEntity;
    private List<DocumentosEntity> documentos;
    private List<TelefonesEntity> telefones;
    private List<EmailEmtity> emailEmtities;

}
