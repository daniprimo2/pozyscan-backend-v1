package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

import lombok.Builder;

@Builder
public record SelectFornecedoresResponse(String nomeFornecedor, Long idFornecedor) {
}
