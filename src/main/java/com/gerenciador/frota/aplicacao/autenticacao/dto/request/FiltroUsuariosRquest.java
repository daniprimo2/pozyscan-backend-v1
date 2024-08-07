package com.gerenciador.frota.aplicacao.autenticacao.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltroUsuariosRquest {

    private String nomeUsuario;
    private String emailUsuario;
    private String idUsuario;

}
