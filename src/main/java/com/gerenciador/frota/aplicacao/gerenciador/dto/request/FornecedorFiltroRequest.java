package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorFiltroRequest {

    private String nomeFornecedor;
    private String cnpjFornecedor;
    private String idFornecedor;

}
