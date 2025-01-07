package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefonesRequest {

    @NotBlank(message = "Deve ser informado o DD.")
    private String dd;
    @NotBlank(message = "Deve ser informado o numero do telefone.")
    private String telefone;
    private String observacoes;
    private TipoContato tipoContato;

    public TelefonesEntity cadastrarTelefone(ColaboradorEntity colaboradorEntity) {
        return TelefonesEntity.builder()
                .colaboradorEntity(colaboradorEntity)
                .dd(this.dd)
                .observacoes(this.observacoes)
                .telefone(this.telefone)
                .tipoContato(this.tipoContato)
                .build();
    }

    public TelefonesEntity construirTelefone(ColaboradorEntity colaboradorEntity) {
        return TelefonesEntity.builder()
                .colaboradorEntity(colaboradorEntity)
                .dd(this.dd)
                .build();
    }
}
