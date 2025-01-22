package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.integracoes.repository.EnderecoRepository;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaNotaFiscalLogisticaRepository;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.NotaFiscalLogisticaRepositoryPort;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.MappersDominio;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.MappersJpaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.MappersRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotaFiscalLogisticaImplementacao implements NotaFiscalLogisticaRepositoryPort {

    private final JpaNotaFiscalLogisticaRepository notaFiscalLogisticaRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepSerive viaCepSerive;

    @Override
    public NotaFiscalLogistica cadastrarNotaFiscal(NotaFiscalLogisticaRequest request) {
        log.info("[START] - Inicio de cadastrar uma nova nota fiscal.");
        try {
            Endereco endereco = MappersDominio.fromEnderecoRequestToEndereco(request.getEnderecoRequest(), viaCepSerive);
            log.info("[INFO] - Salvar endereco.");
            Endereco enderecoSalvo = enderecoRepository.save(endereco);
            log.info("[INFO] - Salvar endereco com o id: {} e cep: {}.", enderecoSalvo.getCodigoEndereco(), enderecoSalvo.getCep());
            JpaNotaFiscalLogisticaEntity entity = MappersRequest.fromNotaFiscaLogisticaToJpaNotaFiscalLogisticaEntity(request,request.getEnderecoRequest() == null ? null : enderecoSalvo);

            log.info("[INFO] - Entity que esta sendo salvo: {}", entity);
            var notaFiscalSalvo = notaFiscalLogisticaRepository
                    .save(entity);

            log.info("[END] - Nota fiscal foi salvo.");
            return MappersDominio
                    .fromJpaNotaFiscalLogisticaEntityToNotaFiscalLogistica(notaFiscalSalvo);
        } catch (Exception ex) {
            log.info("[ERRO] - Falhou ao gerar uma nova Nota fiscal. {}", ex.getMessage());
            throw new RuntimeException("Falhou ao salvar uma nova nota fiscal. ");
        }
    }

    @Override
    public List<NotaFiscalLogistica> buscarTodasNotasFiscais() {
            log.info("[START] - Buscar todas as notas fiscais.");
        try {
            var todasNotasFiscais = notaFiscalLogisticaRepository.findAll();
            log.info("[INFO] - Foi encontrado {} notas fiscais.", todasNotasFiscais.size());
            log.info("[INFO] - Converter lista de model para a jpa entity.");
            var listaNotaFiscalConvertidaParaModel = MappersDominio.fromListNotaFiscalToListJpaNotaFiscalEntity(todasNotasFiscais);
            log.info("[END] - Lista convertida para uma lista de  models.");
            return listaNotaFiscalConvertidaParaModel;
        } catch (Exception ex) {
            log.info("[ERRO] - Falhou ao buscar todas notas fiscais.");
            throw new RuntimeException("Falhou ao buscar todas as notas fiscais.");
        }
    }

    @Override
    public NotaFiscalLogistica buscarNotasFiscaisPorCodigo(Long codigoNotaFiscal) {
        log.info("[START] - Buscar Nota Fiscal pelo codigo: {}.",codigoNotaFiscal);
            var notaFiscal = notaFiscalLogisticaRepository.findById(codigoNotaFiscal)
                    .orElseThrow(() -> new RuntimeException("Nota Fiscal não encontrada."));
            log.info("[END] - Nota fiscal encontrada: "+codigoNotaFiscal+".");
            return MappersDominio.fromJpaNotaFiscalLogisticaEntityToNotaFiscalLogistica(notaFiscal);
    }

    public JpaNotaFiscalLogisticaEntity buscarNotasFiscaisPorCodigoJpa(Long codigoNotaFiscal) {
        log.info("[START] - Buscar Nota Fiscal pelo codigo: {}.",codigoNotaFiscal);
        var notaFiscal = notaFiscalLogisticaRepository.findById(codigoNotaFiscal)
                .orElseThrow(() -> new RuntimeException("Nota Fiscal não encontrada."));
        log.info("[END] - Nota fiscal encontrada: "+codigoNotaFiscal+".");
        return notaFiscal;
    }

    @Override
    public RetornoServicoBase atualziarNotaFiscal(Long codigoNotaFiscal, NotaFiscalLogisticaRequest request) {
        try {
            log.info("[START] - Atualizar nota fiscal de codigo: {}", codigoNotaFiscal);
            var notaFiscalLogisticaRecuperado = this.buscarNotasFiscaisPorCodigo(codigoNotaFiscal);
            log.info("[INFO] - Nota fiscal de codigo: {} encontrada com sucesso.", codigoNotaFiscal);
            var notaFiscalAtualizado = MappersJpaEntity.fromNotaFiscalRequestToJpaNotaFiscalLogisticaEntity(request, notaFiscalLogisticaRecuperado);
            Endereco enderecoAtualizado = enderecoRepository.save(notaFiscalAtualizado.getEndereco());
            notaFiscalAtualizado.setEndereco(enderecoAtualizado);
            log.info("[INFO] - Entity foi atualizada pacote pronto para salvar.");
            notaFiscalLogisticaRepository.save(notaFiscalAtualizado);
            log.info("[END] - Nota fiscal atualizada com sucesso.");
            return RetornoServicoBase.positivo("Nota fiscal codigo: "+codigoNotaFiscal+" e numero: "+request.getNumeroNotaFisal()+" atualizada com sucesso.");
        } catch (Exception ex) {
            log.info("[ERRO] - Falhou ao atualziar nota fiscal.");
            return RetornoServicoBase.negativo("Falhou ao atualizar a Nota fiscal codigo: "+codigoNotaFiscal+".");
        }
    }

    @Override
    public RetornoServicoBase deletarNotaFiscal(Long codigoNotaFiscal) {
        try {
            log.info("[START] - Deletar nota fiscal de codigo: {}", codigoNotaFiscal);
            var notaFiscalLogistica = this.buscarNotasFiscaisPorCodigoJpa(codigoNotaFiscal);
            log.info("[END] - Deletar nota fiscal de codigo: {}.", codigoNotaFiscal);
            notaFiscalLogisticaRepository.delete(notaFiscalLogistica);
            log.info("[END] - Nota fiscal atualizada com sucesso.");
            return RetornoServicoBase.positivo("Nota fiscal codigo: "+codigoNotaFiscal+" deletado com sucesso.");
        } catch (Exception ex) {
            log.info("[ERRO] - Falhou ao deletar nota fiscal.");
            return RetornoServicoBase.negativo("Falhou ao deletar a Nota fiscal codigo: "+codigoNotaFiscal+".");
        }    }
}
