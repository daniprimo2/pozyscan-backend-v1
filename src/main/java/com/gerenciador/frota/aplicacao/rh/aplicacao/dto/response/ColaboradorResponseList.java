package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DadosPessoaisEntity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorResponseList {

    private Long id;
    private String descricaoAtividade;
    private StatusColaborador status;
    private String dataContratacao;
    private String dataDemissao;
    private DadosPessoaisEntity dadosPessoaisEntity;
    private CargoEntity cargoEntity;
    private List<TelefonesResponse> telefones = new ArrayList<>();
    private List<EmailResponse> emailS = new ArrayList<>();
    private List<DocumentosResponse> documentos = new ArrayList<>();

}
