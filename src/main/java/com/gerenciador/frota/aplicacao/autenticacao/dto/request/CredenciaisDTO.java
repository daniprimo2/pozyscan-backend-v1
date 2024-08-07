package com.gerenciador.frota.aplicacao.autenticacao.dto.request;

import lombok.Builder;

@Builder
public record CredenciaisDTO(String login, String password) {

}
