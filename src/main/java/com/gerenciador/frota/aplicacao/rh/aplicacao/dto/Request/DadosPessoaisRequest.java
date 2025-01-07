package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DadosPessoaisEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisRequest {

    @NotBlank(message = "Deve ser informado o nome completp.")
    private String nomeCompleto;
    @NotBlank(message = "Deve ser informado a data de nascimento.")
    private String dataNascimento;
    private String nomeDaMae;
    private String nomeDoPai;

    public DadosPessoaisEntity construirDadosPessoais(Endereco endereco) {
        return DadosPessoaisEntity.builder()
                .nomeCompleto(this.nomeCompleto)
                .dataNascimento(this.dataNascimento)
                .nomeMae(this.nomeDaMae)
                .nomePai(this.nomeDoPai)
                .endereco(endereco)
                .build();
    }
}
