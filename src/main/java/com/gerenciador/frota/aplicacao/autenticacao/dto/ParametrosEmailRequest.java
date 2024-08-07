package com.gerenciador.frota.aplicacao.autenticacao.dto;

import com.gerenciador.frota.aplicacao.autenticacao.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosEmailRequest {

    private String nomeParticipante;
    private String login;
    private String senha;

    public static ParametrosEmailRequest costruirParametrosCom(Usuario usuarioSalvo) {
        return ParametrosEmailRequest.builder()
                .login(usuarioSalvo.getLogin())
                .senha(usuarioSalvo.getLogin())
                .nomeParticipante(usuarioSalvo.getNome())
                .build();
    }
}
