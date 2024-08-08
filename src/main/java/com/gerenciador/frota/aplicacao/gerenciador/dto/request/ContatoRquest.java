package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.FornecedorRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Contato;
import com.gerenciador.frota.aplicacao.gerenciador.model.EmailContato;
import com.gerenciador.frota.aplicacao.gerenciador.model.Telefone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContatoRquest {

    private String nomeContato;
    private String idDoFornecedor;
    private List<EmailRquest> emails;
    private List<TelefoneRequest> telefones;

    public Contato construindoContato(FornecedorRepository fornecedorRepository) {
        return Contato.builder()
                .nome(nomeContato)
                .fornecedor(fornecedorRepository.findById(Long.valueOf(idDoFornecedor))
                        .orElseThrow(() -> new RuntimeException("Id do Fornecedor não foi encontrado.")))
                .emails(converterRquestParaDomitio(emails))
                .telefones(converterRquestParaDomitioTelefone(telefones))
                .build();
    }

    private List<Telefone> converterRquestParaDomitioTelefone(List<TelefoneRequest> telefones) {
        return telefones.stream().map(telefoneRequest -> {
            return Telefone.builder()
                    .numero(telefoneRequest.getTelefone())
                    .tipo(telefoneRequest.getTipo())
                    .build();

        }).toList();
    }

    private List<EmailContato> converterRquestParaDomitio(List<EmailRquest> emails) {
        return emails.stream().map(emailRquest -> {
            return EmailContato.builder()
                    .tipo(emailRquest.getTipo())
                    .email(emailRquest.getEmail())
                    .build();
        }).toList();

    }
}
