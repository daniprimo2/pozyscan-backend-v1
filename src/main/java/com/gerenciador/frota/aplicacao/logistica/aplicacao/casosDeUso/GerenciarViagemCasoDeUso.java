package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso.IViagemCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.adapters.ViagemRepositoryAdapters;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request.ViagemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GerenciarViagemCasoDeUso implements IViagemCasoDeUso {

    private final ViagemRepositoryAdapters adapters;


    @Override
    public Viagem cadastrarNovaViagem(ViagemRequest request) {
        return adapters.salvar(request);
    }


}
