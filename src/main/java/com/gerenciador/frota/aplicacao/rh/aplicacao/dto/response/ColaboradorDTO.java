package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorDTO {

    private Long codigoColaborador;
    private String dataContratacao;
    private String dataDemissao;
    private String descricaoAtividade;
    private StatusColaborador status;
    private Long codigoCargo;
    private Long codigoDadosPessoais;

    public ColaboradorDTO construirColaborador(ColaboradorEntity colaboradorEntity) {
        this.codigoColaborador = colaboradorEntity.getId();
        this.dataContratacao = colaboradorEntity.getDataContratacao();
        this.dataDemissao = colaboradorEntity.getDataDemissao();
        this.descricaoAtividade = colaboradorEntity.getDescricaoAtividade();
        this.status = colaboradorEntity.getStatus();
        this.codigoCargo = colaboradorEntity.getCargoEntity().getId();
        this.codigoDadosPessoais = colaboradorEntity.getDadosPessoaisEntity().getId();
        return this;
    }
}
