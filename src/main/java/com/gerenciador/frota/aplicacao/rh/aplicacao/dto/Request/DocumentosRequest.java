package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DocumentosEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentosRequest {

    @NotBlank(message = "Numero do documento deve ser informado.")
    private String numeroDocumento;
    private String dataExpedicao;
    private String dataValidade;
    private String orgaoEmissor;
    private String arquivoBase64;
    @NotBlank(message = "Deve ser informado o tipo do documento.")
    private TipoDocumento tipoDocumento;

    public DocumentosEntity cosntruirDocumento(ColaboradorEntity colaboradorEntity) {
        return DocumentosEntity.builder()
                .tipoDocumento(this.tipoDocumento)
                .arquivoBase64(this.arquivoBase64)
                .colaboradorEntity(colaboradorEntity)
                .dataExpedicao(this.dataExpedicao)
                .dataValidade(this.dataValidade)
                .orgaoEmissor(this.orgaoEmissor)
                .numeroDocumento(this.numeroDocumento)
                .build();
    }
}
