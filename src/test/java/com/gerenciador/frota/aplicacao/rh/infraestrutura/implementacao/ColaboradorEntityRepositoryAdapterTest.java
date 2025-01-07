package com.gerenciador.frota.aplicacao.rh.infraestrutura.implementacao;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.integracoes.repository.EnderecoRepository;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.*;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao.ColaboradorRepositoryAdapter;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarCargoCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.ColaboradorResponseList;
import com.gerenciador.frota.aplicacao.rh.dominio.exceptions.ColaboradorNaoFoiSalvoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ColaboradorEntityRepositoryAdapterTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @InjectMocks
    public ColaboradorRepositoryAdapter colaboradorRepositoryAdapter;

    @Mock
    private GerenciarCargoCasoDeUso gerenciarCargoCasoDeUso;

    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private DadosPessoaisRepository dadosPessoaisRepository;

    @Mock
    private ViaCepSerive viaCepSerive;

    @Mock
    private DocumentosRepository documentosRepository;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private TelRepository telRepository;
    private ColaboradorResponseList colaboradorResponseList;

    private ColaboradorEntity colaboradorEntity;
    private ColaboradorRequest colaboradorRequest;

    private CargoEntity cargoEntity;

    private DadosPessoaisEntity dadosPessoaisEntity;

    private Endereco endereco;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.cargoEntity = CargoEntity.builder().id(1l).nomeCargo("GERENTE").descricaoCargo("GERENTE").tipoCargo(TipoCargo.GERENTE).build();

        colaboradorEntity = ColaboradorEntity.builder()
                .id(1L)
                .descricaoAtividade("Atividade de Teste")
                .status(StatusColaborador.ATIVO)
                .dataContratacao(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .dadosPessoaisEntity(DadosPessoaisEntity.builder()
                        .nomeCompleto("Teste Completo")
                        .dataNascimento("01/01/2000")
                        .build())
                .telefones(Collections.emptyList())
                .emailEmtities(Collections.emptyList())
                .documentos(Collections.emptyList())
                .cargoEntity(this.cargoEntity)
                .build();

        colaboradorRequest = ColaboradorRequest.builder()
                .descricaoAtividade("Atividade Atualizada")
                .dadosPessoaisRequest(DadosPessoaisRequest.builder()
                        .nomeCompleto("Nome Atualizado")
                        .dataNascimento("02/02/2000")
                        .build())
                .telefones(List.of(
                        TelefonesRequest.builder()
                                .dd("11")
                                .telefone("999999999")
                                .tipoContato(TipoContato.PESSOAL)
                                .build()
                ))
                .build();

        this.endereco = Endereco.builder().uf("SP").complemento("").codigoEndereco(4L).cep("06385820").numero("141").logradouro("Rua Ipixuna").bairro("vila menk").estado("São Paulo").localidade("Carapicuina").build();
        this.dadosPessoaisEntity = DadosPessoaisEntity.builder().id(1l).nomeCompleto("Daniel Lopes Sousa Moreira").dataNascimento("05/08/1999").nomePai("Paulo Roberto").nomeMae("Eronice Cristina Lopes Sousa Moreira")
                .endereco(this.endereco).build();

        gerenciarCargoCasoDeUso = mock(GerenciarCargoCasoDeUso.class); // Inicializando o mock

        this.colaboradorResponseList = new ColaboradorResponseList(1l, "",StatusColaborador.ATIVO,LocalDate.now().toString(), "", this.dadosPessoaisEntity, this.cargoEntity, null, null, null);

    }

    @Test
    void deveSalvarColaboradorComSucesso() {
        when(colaboradorRepository.save(any(ColaboradorEntity.class))).thenReturn(colaboradorEntity);

        ColaboradorEntity resultado = colaboradorRepositoryAdapter.salva(colaboradorEntity);

        assertNotNull(resultado);
        assertEquals("Atividade de Teste", resultado.getDescricaoAtividade());
        verify(colaboradorRepository, times(1)).save(colaboradorEntity);
    }

    @Test
    void deveBuscarColaboradorPorIdComSucesso() {
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaboradorEntity));


        ColaboradorResponseList resultado = colaboradorRepositoryAdapter.buscarColaboradorPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(colaboradorRepository, times(1)).findById(1L);
    }



    @Test
    void deveLancarExcecaoAoBuscarColaboradorNaoExistente() {
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> colaboradorRepositoryAdapter.buscarColaboradorPorId(1L));
        verify(colaboradorRepository, times(1)).findById(1L);
    }

    @Test
    void deveExcluirColaboradorComSucesso() {
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaboradorEntity));
        doNothing().when(colaboradorRepository).delete(colaboradorEntity);

        colaboradorRepositoryAdapter.DEletaarColaborador(1L);

        verify(colaboradorRepository, times(1)).delete(colaboradorEntity);
    }

    @Test
    @Disabled("Nao esta sendo possivel criar este teste")
    void deveSalvarColaboradorComRequestComSucesso() {
        // Criar e configurar o enderecoRequest mockado
        EnderecoRequest enderecoRequest = mock(EnderecoRequest.class);
        when(enderecoRequest.getCep()).thenReturn("12345-678");

        // Criar o colaboradorRequest com o enderecoRequest não nulo
        ColaboradorRequest colaboradorRequest = new ColaboradorRequest();
        colaboradorRequest.setEnderecoRequest(enderecoRequest);

        // Simula a criação do colaborador a partir do request
        when(gerenciarCargoCasoDeUso.buscarPorId(anyLong())).thenReturn(cargoEntity);
        when(colaboradorRepository.save(any(ColaboradorEntity.class))).thenReturn(colaboradorEntity);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(dadosPessoaisRepository.save(any(DadosPessoaisEntity.class))).thenReturn(dadosPessoaisEntity);
        when(documentosRepository.save(any(DocumentosEntity.class))).thenReturn(new DocumentosEntity());
        when(telRepository.save(any(TelefonesEntity.class))).thenReturn(new TelefonesEntity());
        when(emailRepository.save(any(EmailEmtity.class))).thenReturn(new EmailEmtity());

        ColaboradorEntity resultado = colaboradorRepositoryAdapter.salva(colaboradorRequest);

        assertNotNull(resultado);
        assertEquals("Atividade de Teste", resultado.getDescricaoAtividade());
        verify(colaboradorRepository, times(1)).save(any(ColaboradorEntity.class));
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
        verify(dadosPessoaisRepository, times(1)).save(any(DadosPessoaisEntity.class));
        verify(documentosRepository, times(1)).save(any(DocumentosEntity.class));
        verify(telRepository, times(1)).save(any(TelefonesEntity.class));
        verify(emailRepository, times(1)).save(any(EmailEmtity.class));
    }

    @Test
    void deveDeletarColaboradorComSucesso() {
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaboradorEntity));
        doNothing().when(colaboradorRepository).delete(colaboradorEntity);

        RetornoServicoBase retorno = colaboradorRepositoryAdapter.DEletaarColaborador(1L);

        assertEquals("Colaborador deletado com sucesso.", retorno.getDescricao());
        verify(colaboradorRepository, times(1)).delete(colaboradorEntity);
    }

    @Test
    void deveRetornarErroAoDeletarColaboradorNaoExistente() {
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.empty());

        RetornoServicoBase retorno = colaboradorRepositoryAdapter.DEletaarColaborador(1L);

        assertEquals("Não foi possível deletar o colaborador de código: 1", retorno.getDescricao());
        verify(colaboradorRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoSalvarColaboradorComErro() {
        when(colaboradorRepository.save(any(ColaboradorEntity.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        assertThrows(ColaboradorNaoFoiSalvoException.class, () -> colaboradorRepositoryAdapter.salva(colaboradorEntity));
    }

}
