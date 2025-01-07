package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefonesResponse {
    private Long id;
    private String dd;
    private String telefone;
    private String observacoes;
    private TipoContato tipoContato;



}
