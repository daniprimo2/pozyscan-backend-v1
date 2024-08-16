package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

import lombok.Builder;

@Builder
public record SelectFilialResponse(String nomeFilial, Long idFilial) {
}
