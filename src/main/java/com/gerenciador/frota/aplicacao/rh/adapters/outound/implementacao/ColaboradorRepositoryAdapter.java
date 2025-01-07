package com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.integracoes.repository.EnderecoRepository;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.*;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarCargoCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.*;
import com.gerenciador.frota.aplicacao.rh.dominio.exceptions.ColaboradorNaoFoiSalvoException;
import com.gerenciador.frota.aplicacao.rh.dominio.exceptions.EnderecoNaoFoiSlavoException;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.ColaboradorRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class ColaboradorRepositoryAdapter implements ColaboradorRepositoryPort {

    private final ColaboradorRepository colaboradorRepository;
    private final DadosPessoaisRepository dadosPessoaisRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepSerive viaCepSerive;
    private final DocumentosRepository documentosRepository;
    private final GerenciarCargoCasoDeUso gerenciarCargoCasoDeUso;
    private final EmailRepository emailRepository;
    private final TelRepository telRepository;



    @Override
    public ColaboradorEntity salva(ColaboradorRequest colaboradorRequest)  {
        log.info("Criar colaborador {}", colaboradorRequest.getDadosPessoaisRequest().getNomeCompleto());

        try{
            var colaborador = colaboradorRequest.construirColaborador(gerenciarCargoCasoDeUso);
            var endereco = cadastrarEnderecoNoBanco(colaboradorRequest.getEnderecoRequest());
            colaborador.setDadosPessoaisEntity(cadastrarDadosPessoaisNoBanco(colaboradorRequest.getDadosPessoaisRequest(), endereco));
            colaborador = colaboradorRepository.save(colaborador);
            associarInformacoesAdicionais(colaborador, colaboradorRequest);
            log.info("Colaborador {} foi salvo com sucesso.", colaborador.getId());
            return colaborador;

        } catch (Exception e) {
            log.error("Erro ao salvar colaborador: {}", e.getMessage(), e);
            throw new ColaboradorNaoFoiSalvoException("Nao Foi possivel salvar o colaborador");
        }
    }


    private void associarInformacoesAdicionais(ColaboradorEntity colaboradorEntity, ColaboradorRequest request) {
        colaboradorEntity.setDocumentos(cadastrarDocumentos(request.getDocumentosRequestList(), colaboradorEntity));
        colaboradorEntity.setTelefones(cadastrarTelefones(request.getTelefones(), colaboradorEntity));
        colaboradorEntity.setEmailEmtities(cadastrarEmails(request.getEmails(), colaboradorEntity));
    }

    private List<DocumentosEntity> cadastrarDocumentos(List<DocumentosRequest> documentosRequest, ColaboradorEntity colaboradorEntity) {
        return documentosRequest.stream()
                .map(request -> request.cosntruirDocumento(colaboradorEntity))
                .map(documentosRepository::save)
                .toList();
    }

    private List<TelefonesEntity> cadastrarTelefones(List<TelefonesRequest> telefonesRequest, ColaboradorEntity colaboradorEntity) {
        return telefonesRequest.stream()
                .map(request -> request.cadastrarTelefone(colaboradorEntity))
                .map(telRepository::save)
                .toList();
    }

    private List<EmailEmtity> cadastrarEmails(List<EmailRequest> emailsRequest, ColaboradorEntity colaboradorEntity) {
        return emailsRequest.stream()
                .map(request -> request.construirEmails(colaboradorEntity))
                .map(emailRepository::save)
                .toList();
    }


    @Override
    public ColaboradorEntity salva(ColaboradorEntity colaboradorEntity) throws ColaboradorNaoFoiSalvoException {
        try {
            colaboradorEntity = colaboradorRepository.save(colaboradorEntity);
            log.info("Colaborador {} foi salvo com sucesso.", colaboradorEntity.getId());
        } catch (Exception e) {
            log.error("Erro ao salvar colaborador: {}", e.getMessage(), e);
            throw new ColaboradorNaoFoiSalvoException("Não foi possível salvar o colaborador.");
        }
        return colaboradorEntity;
    }

    @Override
    public List<ColaboradorResponseList> listaDeColaboradores() {
        return colaboradorRepository.findAll().stream()
                .map(this::mapearColaboradorParaResponse)
                .toList();
    }
    @Override
    public ColaboradorResponseList buscarColaboradorPorId(Long id) {
        return colaboradorRepository.findById(id)
                .map(this::mapearColaboradorParaResponse)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado."));
    }

    @Override
    public RetornoServicoBase DEletaarColaborador(Long id) {
        try {
            ColaboradorEntity colaboradorEntity = colaboradorRepository.findById(id)
                    .orElseThrow(() -> new ColaboradorNaoFoiSalvoException("Colaborador não encontrado para o ID: " + id));

            colaboradorRepository.delete(colaboradorEntity);
            log.info("Colaborador de ID {} deletado com sucesso.", id);
            return RetornoServicoBase.positivo("Colaborador deletado com sucesso.");
        } catch (Exception ex) {
            log.error("Erro ao deletar colaborador de ID {}: {}", id, ex.getMessage(), ex);
            return RetornoServicoBase.negativo("Não foi possível deletar o colaborador de código: " + id);
        }
    }

    private Endereco cadastrarEnderecoNoBanco(EnderecoRequest enderecoRequest) {
        log.info("Construindo colaborador com o CEP {}", enderecoRequest.getCep());
        try {
            return enderecoRepository.save(enderecoRequest.construirEndereco(viaCepSerive));
        } catch (IllegalArgumentException e) {
            log.warn("ViaCep não populou os campos do endereço para o CEP {}: {}", enderecoRequest.getCep(), e.getMessage());
            return enderecoRepository.save(enderecoRequest.construirEndereco());
        } catch (Exception e) {
            log.error("Erro ao cadastrar endereço: {}", e.getMessage(), e);
            throw new EnderecoNaoFoiSlavoException("Não foi possível cadastrar o endereço.");
        }
    }

    private DadosPessoaisEntity cadastrarDadosPessoaisNoBanco(DadosPessoaisRequest dadosPessoaisRequest, Endereco endereco) {
        DadosPessoaisEntity dadosPessoaisEntity;
        try{
            dadosPessoaisEntity = dadosPessoaisRepository.save(dadosPessoaisRequest.construirDadosPessoais(endereco));
        } catch (Exception e) {
            throw new RuntimeException("Dados Pessoais Não pode ser cadastrado.");
        }
        return dadosPessoaisEntity;
    }

    private ColaboradorResponseList mapearColaboradorParaResponse(ColaboradorEntity colaboradorEntity) {
        return ColaboradorResponseList.builder()
                .id(colaboradorEntity.getId())
                .descricaoAtividade(colaboradorEntity.getDescricaoAtividade())
                .status(colaboradorEntity.getStatus())
                .dataContratacao(colaboradorEntity.getDataContratacao())
                .dataDemissao(colaboradorEntity.getDataDemissao())
                .cargoEntity(gerenciarCargoCasoDeUso.buscarPorId(colaboradorEntity.getCargoEntity().getId()))
                .dadosPessoaisEntity(colaboradorEntity.getDadosPessoaisEntity())
                .documentos(buscarDocumentosDoColaborador(colaboradorEntity.getId()))
                .telefones(buscarTelefonesDoColaborador(colaboradorEntity.getId()))
                .emailS(buscarEmailsDoColaborador(colaboradorEntity.getId()))
                .build();
    }

    private List<DocumentosResponse> buscarDocumentosDoColaborador(Long colaboradorId) {
        return documentosRepository.findBycolaboradorEntity(colaboradorId).stream()
                .map(DocumentosEntity::construirDocumentoResponse)
                .toList();
    }

    private List<TelefonesResponse> buscarTelefonesDoColaborador(Long colaboradorId) {
        return telRepository.findBycolaboradorEntity(colaboradorId).stream()
                .map(TelefonesEntity::construirResponse)
                .toList();
    }

    private List<EmailResponse> buscarEmailsDoColaborador(Long colaboradorId) {
        return emailRepository.findBycolaboradorEntity(colaboradorId).stream()
                .map(EmailEmtity::construirResponse)
                .toList();
    }

}
