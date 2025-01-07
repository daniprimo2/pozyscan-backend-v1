package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentosResponse {
    private Long id;
    private String numeroDocumento;
    private String dataExpedicao;
    private String dataValidade;
    private String orgaoEmissor;
    private String arquivoBase64;
    private TipoDocumento tipoDocumento;

}
