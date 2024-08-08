package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContatoRequestForncedor {

    private String nomeContato;
    private List<EmailRquest> emails;
    private List<TelefoneRequest> telefones;

}
