package com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.ColaboradorRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.ColaboradorResponseList;
import com.gerenciador.frota.aplicacao.rh.dominio.exceptions.ColaboradorNaoFoiSalvoException;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;

import java.util.List;

public interface ColaboradorRepositoryPort {

    ColaboradorEntity salva(ColaboradorRequest colaborador) throws ColaboradorNaoFoiSalvoException;

    ColaboradorEntity salva(ColaboradorEntity colaboradorEntity) throws ColaboradorNaoFoiSalvoException;

    List<ColaboradorResponseList> listaDeColaboradores();

    ColaboradorResponseList buscarColaboradorPorId(Long id);

    RetornoServicoBase DEletaarColaborador(Long id);


}
