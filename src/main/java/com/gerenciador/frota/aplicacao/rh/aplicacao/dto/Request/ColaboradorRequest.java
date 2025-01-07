package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarCargoCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorRequest {

    private Long codigoDoCargo;
    private String descricaoAtividade;
    private DadosPessoaisRequest dadosPessoaisRequest;
    private EnderecoRequest enderecoRequest;
    private List<DocumentosRequest> documentosRequestList;
    private List<TelefonesRequest> telefones;
    private List<EmailRequest> emails;

    public ColaboradorEntity construirColaborador(GerenciarCargoCasoDeUso cargoInfraestrutura) {
        return ColaboradorEntity.builder()
                .dataContratacao(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .status(StatusColaborador.ATIVO)
                .descricaoAtividade(this.descricaoAtividade)
                .cargoEntity(cargoInfraestrutura.buscarPorId(this.codigoDoCargo))
                .build();
    }
}
