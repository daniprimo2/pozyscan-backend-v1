package com.gerenciador.frota.aplicacao.logistica.utils.mappers;

import com.gerenciador.frota.aplicacao.gerenciador.model.Veiculo;
import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.EnderecoRequest;

public class MappersDominio {

    public static NotaFiscalLogistica fromJpaNotaFiscalLogisticaEntityToNotaFiscalLogistica(JpaNotaFiscalLogisticaEntity notaFiscalLogisticaEntity) {
        return new NotaFiscalLogistica(notaFiscalLogisticaEntity.getCodigoNotaFiscal(),
                notaFiscalLogisticaEntity.getNumeroNotaFisal(),
                notaFiscalLogisticaEntity.getValorTotal(),
                notaFiscalLogisticaEntity.getDataEmissao(),
                MappersDominio.fromJpaRemessaEntityToRemessa(notaFiscalLogisticaEntity.getJpaRemessaEntity()),
                notaFiscalLogisticaEntity.getEndereco());
    }

    private static Remessa fromJpaRemessaEntityToRemessa(JpaRemessaEntity jpaRemessaEntity) {
        return new Remessa(
                jpaRemessaEntity.getId(),
                jpaRemessaEntity.getCliente(),
                jpaRemessaEntity.getDataCriacao(),
                jpaRemessaEntity.getVolumeTotal(),
                jpaRemessaEntity.getPesoTotal(),
                jpaRemessaEntity.getStatusRemessa(),
                jpaRemessaEntity.getJpaViagemEntity() == null ? null : fromJpaViagemEntityToViagem(jpaRemessaEntity.getJpaViagemEntity()),
                null);
    }

    private static Viagem fromJpaViagemEntityToViagem(JpaViagemEntity jpaViagemEntity) {
        return new Viagem(jpaViagemEntity.getId(),
                jpaViagemEntity.getDataCriacao(),
                jpaViagemEntity.getDataProgramadaViagem(),
                jpaViagemEntity.getDataRealizadoViagem(),
                jpaViagemEntity.getVolumeTotal(),
                jpaViagemEntity.getPesoTotal(),
                jpaViagemEntity.getTotalKilometragem(),
                jpaViagemEntity.getTotalRemessa(),
                jpaViagemEntity.getVeiculo() == null ? null : jpaViagemEntity.getVeiculo(),
                jpaViagemEntity.getTipoViagem(),
                jpaViagemEntity.getViagem().getRemessas());
    }


    public static Endereco fromEnderecoRequestToEndereco(EnderecoRequest enderecoRequest) {
        return Endereco.builder()
                .localidade(enderecoRequest.getLocalidade())
                .estado(enderecoRequest.getEstado())
                .uf(enderecoRequest.getUf())
                .cep(enderecoRequest.getCep())
                .bairro(enderecoRequest.getBairro())
                .complemento(enderecoRequest.getComplemento())
                .numero(enderecoRequest.getNumero())
                .build();
    }

    public static Endereco fromEnderecoRequestToEndereco(EnderecoRequest enderecoRequest, ViaCepSerive viaCepSerive) {
        EnderecoResponse enderecoResponse = viaCepSerive.buscarEnderecoPorCep(enderecoRequest.getCep());
        return Endereco.builder()
                .localidade(enderecoResponse.getLocalidade())
                .estado(enderecoResponse.getEstado())
                .complemento(enderecoRequest.getComplemento())
                .uf(enderecoResponse.getUf())
                .cep(enderecoResponse.getCep())
                .bairro(enderecoResponse.getBairro())
                .complemento(enderecoRequest.getComplemento())
                .numero(enderecoRequest.getNumero())
                .build();
    }
}
