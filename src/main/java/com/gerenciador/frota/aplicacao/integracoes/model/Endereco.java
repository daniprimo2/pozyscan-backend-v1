package com.gerenciador.frota.aplicacao.integracoes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_ENDERECO")
    private Long codigoEndereco;
    @Column(name = "COD_CEP")
    private String cep;
    @Column(name = "NOME_LOGRADOURO")
    private String logradouro;
    @Column(name = "NUMERO_LOGRADOURO")
    private String numero;
    @Column(name = "BAIRRO")
    private String bairro;
    @Column(name = "LOCALIDADE")
    private String localidade;
    @Column(name = "UF")
    private String uf;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "COMPLEMENTO")
    private String complemento;


}
