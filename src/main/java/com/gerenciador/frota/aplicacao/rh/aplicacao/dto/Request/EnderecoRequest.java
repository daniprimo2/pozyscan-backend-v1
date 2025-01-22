package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnderecoRequest {

    @NotBlank(message = "Deve ser informado o cep.")
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String complemento;

    public Endereco construirEndereco(ViaCepSerive viaCepSerive) {
        return viaCepSerive.buscarEnderecoPorCep(this.cep, this.numero, this.complemento);
    }

    public Endereco construirEndereco() {
        return Endereco.builder()
                .cep(this.cep)
                .logradouro(this.logradouro)
                .bairro(this.bairro)
                .numero(numero)
                .localidade(this.localidade)
                .uf(this.uf)
                .estado(this.estado)
                .complemento(this.complemento)
                .build();
    }
}
