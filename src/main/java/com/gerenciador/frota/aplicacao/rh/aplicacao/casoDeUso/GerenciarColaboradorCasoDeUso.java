package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.ColaboradorResponseList;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.ColaboradorRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
@Slf4j
public class GerenciarColaboradorCasoDeUso {


    private final ColaboradorRepositoryPort colaboradorRepositoryPort;
    private final GerenciarDocumentoCasoDeUso gerenciarDocumentoCasoDeUso;
    private final GerenciarTelefoneCasoDeUso gerenciarTelefoneCasoDeUso;
    private final GerenciarEmailCasoDeUso gerenciarEmailCasoDeUso;



    public ColaboradorEntity criarColaborador(ColaboradorRequest colaboradorRequest) {
        return colaboradorRepositoryPort.salva(colaboradorRequest);
    }

    public List<ColaboradorResponseList> listaDeColaboradores() {
        return colaboradorRepositoryPort.listaDeColaboradores();
    }

    public ColaboradorResponseList buscarColaboradorPorId (Long id) {
        return colaboradorRepositoryPort.buscarColaboradorPorId(id);
    }

    public RetornoServicoBase atualizarColaborador(Long id, ColaboradorRequest colaboradorRequest){

        ColaboradorResponseList colaboradorResponseList = this.buscarColaboradorPorId(id);
        ColaboradorEntity colaboradorEntity = new ColaboradorEntity();
        ColaboradorEntity colaboraoorModelConstruida = colaboradorEntity.fromColaborador(colaboradorResponseList);
        colaboraoorModelConstruida.atualizarDadosPessoais(colaboradorRequest.getDadosPessoaisRequest());
        colaboraoorModelConstruida.atualizarDocumentos(colaboradorRequest.getDocumentosRequestList(), gerenciarDocumentoCasoDeUso);
        colaboraoorModelConstruida.atualizarTelefones(colaboradorRequest.getTelefones(), gerenciarTelefoneCasoDeUso);
        colaboraoorModelConstruida.atualizarEmails(colaboradorRequest.getEmails(), gerenciarEmailCasoDeUso);
        try {
            colaboradorRepositoryPort.salva(colaboraoorModelConstruida);
        } catch (Exception ex) {
            return RetornoServicoBase.negativo("Não foi possivel atualizar o colaborador de codigo: "+id);
        }
        return RetornoServicoBase.negativo("Colaborador de codigo: "+id+" atualizado com sucesso.");


    }

    public RetornoServicoBase deletarColaborador(Long id){
        return colaboradorRepositoryPort.DEletaarColaborador(id);
    }






}
