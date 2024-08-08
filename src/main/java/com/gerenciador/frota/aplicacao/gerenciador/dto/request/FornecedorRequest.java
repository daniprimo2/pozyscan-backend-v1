package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import com.gerenciador.frota.aplicacao.gerenciador.model.Contato;
import com.gerenciador.frota.aplicacao.gerenciador.model.EmailContato;
import com.gerenciador.frota.aplicacao.gerenciador.model.Fornecedor;
import com.gerenciador.frota.aplicacao.gerenciador.model.Telefone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FornecedorRequest {

    private String nome;
    private String cnpj;
    private List<ContatoRequestForncedor> contatos;

    public Fornecedor construirFornecedor(FornecedorRequest fornecedorRequest) {
        return Fornecedor.builder()
                .nome(fornecedorRequest.getNome())
                .cnpj(fornecedorRequest.getCnpj())
                .build();
    }

    public List<Contato> converterDeRequestParaDominioContato(Fornecedor fornecedor) {
        return this.contatos.stream().map(contatoRequestForncedor -> {
            return Contato.builder()
                    .nome(contatoRequestForncedor.getNomeContato())
                    .fornecedor(fornecedor)
                    .telefones(converterDeRequestParaDominioTelefones(contatoRequestForncedor.getTelefones()))
                    .emails(converterDeRequestParaDominioEmails(contatoRequestForncedor.getEmails()))
                    .build();
        }).toList();
    }

    public List<Telefone> converterDeRequestParaDominioTelefones(List<TelefoneRequest> telefones) {
        return telefones.stream().map(telefoneRequest -> {
            return Telefone.builder()
                    .numero(telefoneRequest.getTelefone())
                    .tipo(telefoneRequest.getTipo())
                    .build();
        }).toList();
    }

    public List<EmailContato> converterDeRequestParaDominioEmails(List<EmailRquest> emails) {
        return emails.stream().map(emailRquest -> {
            return EmailContato.builder()
                    .email(emailRquest.getEmail())
                    .tipo(emailRquest.getTipo())
                    .build();
        }).toList();
    }
}
