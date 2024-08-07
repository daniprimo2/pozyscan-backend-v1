package com.gerenciador.frota.aplicacao.autenticacao.dto.response;

import lombok.Builder;

@Builder
public record LoginResponseDTO(String token, String refreshToken) {
}
