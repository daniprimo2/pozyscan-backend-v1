package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

import lombok.Builder;

@Builder
public record SelectAplicacaoResponse(String nomeAplicacao, Long idAplicacao) {
}
