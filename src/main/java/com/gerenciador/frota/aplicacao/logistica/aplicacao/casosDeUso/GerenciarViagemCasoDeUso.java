package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.Util.Utils.UtilPaginacao;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso.IViagemCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.ViagemRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ViagemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GerenciarViagemCasoDeUso implements IViagemCasoDeUso {

    private final ViagemRepositoryImplementacao adapters;


    @Override
    public Viagem cadastrarNovaViagem(ViagemRequest request) {
        return adapters.salvar(request);
    }

    @Override
    public List<Viagem> listarTodasAsViagems() {
        return adapters.listar();
    }

    @Override
    public PageImpl<?> listarComFiltroViagem(FiltroViagemRequest filtroRequest, Integer page, Integer size) {
        List<Viagem> listar = adapters.listar(filtroRequest);
        return UtilPaginacao.obterPaginacao(listar, PageRequest.of(page, size));
    }

    public List<JpaViagemEntity> listarComFiltroViagem(FiltroViagemRequest filtroRequest) {
        return adapters.listars(filtroRequest);

    }
    @Override
    public Viagem buscarViagemPorCodigo(Long codigoViagem) {
        return adapters.buscarViagemPorId(codigoViagem);
    }

    @Override
    public RetornoServicoBase deletarViagemPorCodigo(Long codigoViagem) {
        return adapters.deletarViagemPorId(codigoViagem);
    }


}
