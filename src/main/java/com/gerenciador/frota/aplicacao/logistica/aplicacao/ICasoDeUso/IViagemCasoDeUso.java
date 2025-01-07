package com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ViagemRequest;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface IViagemCasoDeUso {

    Viagem cadastrarNovaViagem(ViagemRequest request);
    List<Viagem> listarTodasAsViagems();

    PageImpl<?> listarComFiltroViagem(FiltroViagemRequest filtroRequest, Integer page, Integer size);

    Viagem buscarViagemPorCodigo(Long codigoViagem);

    RetornoServicoBase deletarViagemPorCodigo(Long codigoViagem);

}
