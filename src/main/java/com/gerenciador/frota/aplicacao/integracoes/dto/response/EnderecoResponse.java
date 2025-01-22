package com.gerenciador.frota.aplicacao.integracoes.dto.response;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EnderecoResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String complemento;

    public Endereco getEndereco(String numero) {
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

    public Endereco construirEndereco(String numero, String complemento) {
        return Endereco.builder()
                .cep(this.cep)
                .logradouro(this.logradouro)
                .bairro(this.bairro)
                .numero(numero)
                .complemento(complemento)
                .localidade(this.localidade)
                .uf(this.uf)
                .estado(this.estado)
                .build();
    }
}
