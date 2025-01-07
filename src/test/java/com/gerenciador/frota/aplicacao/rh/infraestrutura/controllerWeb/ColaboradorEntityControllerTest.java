package com.gerenciador.frota.aplicacao.rh.infraestrutura.controllerWeb;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarColaboradorCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.ColaboradorRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.ColaboradorResponseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ColaboradorEntityControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerenciarColaboradorCasoDeUso gerenciarColaboradorCasoDeUso;

    private ColaboradorEntity colaboradorEntityModelo;

    private CargoEntity cargoEntity;

    private DadosPessoaisEntity dadosPessoaisEntity;

    private Endereco endereco;

    private ColaboradorResponseList colaboradorResponseList;

    private List<TelefonesEntity> telefones;

    private List<EmailEmtity> emailEmtity;

    private List<DocumentosEntity> documentos;



    @BeforeEach
    void contextTeste() {
        this.endereco = Endereco.builder().uf("SP").complemento("").codigoEndereco(4L).cep("06385820").numero("141").logradouro("Rua Ipixuna").bairro("vila menk").estado("São Paulo").localidade("Carapicuina").build();
        this.cargoEntity = CargoEntity.builder().id(1l).nomeCargo("GERENTE").descricaoCargo("GERENTE").tipoCargo(TipoCargo.GERENTE).build();
        this.dadosPessoaisEntity = DadosPessoaisEntity.builder().id(1l).nomeCompleto("Daniel Lopes Sousa Moreira").dataNascimento("05/08/1999").nomePai("Paulo Roberto").nomeMae("Eronice Cristina Lopes Sousa Moreira")
                .endereco(this.endereco).build();


        this.colaboradorEntityModelo = ColaboradorEntity.builder()
                .id(1l)
                .cargoEntity(montarCargo())
                .dataContratacao(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .descricaoAtividade("Trabalhador")
                .emailEmtities(montarListaDeEmails(this.colaboradorEntityModelo))
                .status(StatusColaborador.ATIVO)
                .dadosPessoaisEntity(montarDadosPessoais(montarEndereco()))
                .documentos(montarListaDeDocumentos(this.colaboradorEntityModelo))
                .telefones(montarListaDeTelefones(this.colaboradorEntityModelo))
                .build();
        this.colaboradorResponseList = new ColaboradorResponseList(1l, "",StatusColaborador.ATIVO,LocalDate.now().toString(), "", this.dadosPessoaisEntity, this.cargoEntity, null, null, null);


    }

    @Test
    void deveCadastrarColaboradorComSucesso() throws Exception {
        ArgumentCaptor<ColaboradorRequest> captor = ArgumentCaptor.forClass(ColaboradorRequest.class);
        when(gerenciarColaboradorCasoDeUso.criarColaborador(any(ColaboradorRequest.class))).thenReturn(this.colaboradorEntityModelo);
        String requisicaoColaboradorJson = montarColaboradorRequest();

        mockMvc.perform(post("/api/colaboradores/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requisicaoColaboradorJson))
                .andExpect(status().isCreated());

        verify(gerenciarColaboradorCasoDeUso).criarColaborador(captor.capture());
        assertEquals("Daniel Lopes Sousa Moreira", captor.getValue().getDadosPessoaisRequest().getNomeCompleto());

    }
    @Test
    void deveRetornarBadRequestAoCadastrarColaborador() throws Exception {
        when(gerenciarColaboradorCasoDeUso.criarColaborador(any(ColaboradorRequest.class))).thenReturn(this.colaboradorEntityModelo);
        String requisicaoColaboradorJson = "\"{ \"codigoDoCargo\": null }\"";

        mockMvc.perform(post("/api/colaboradores/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requisicaoColaboradorJson))
                .andExpect(status().isBadRequest());
    }


    private List<DocumentosEntity> montarListaDeDocumentos(ColaboradorEntity colaboradorEntityModelo) {
        return Arrays.asList(new DocumentosEntity(5l, "542565487", "15/12/2024", "25/12/2024", "Estado de São Paulo", "", TipoDocumento.RG, colaboradorEntityModelo));
    }

    private List<EmailEmtity> montarListaDeEmails(ColaboradorEntity colaboradorEntityModelo) {
        return Arrays.asList(new EmailEmtity(5L, "daniellsmoreira@gmail.com", "", TipoContato.PESSOAL, colaboradorEntityModelo));
    }

    private List<TelefonesEntity> montarListaDeTelefones(ColaboradorEntity colaboradorEntity) {
        return Arrays.asList(new TelefonesEntity(7l, "11", "985850569", "", TipoContato.PESSOAL, colaboradorEntity));
    }

    private Endereco montarEndereco() {
        return new Endereco(6l, "06385820", "Rua Ipixuna", "154", "Vila Menk", "Carapicuiba", "SP", "São Paulo", "");
    }

    private DadosPessoaisEntity montarDadosPessoais(Endereco endereco) {
        return new DadosPessoaisEntity(2l, "Daniel Lopes Sousa Moreira", "05/08/2024", "Eronice Cristina", "Paulo Roberto", endereco);
    }

    private CargoEntity montarCargo() {
        return new CargoEntity(1l, "Gerente", "Gerentes", TipoCargo.GERENTE);
    }



    private String montarColaboradorRequest() {
        return  "{\n" +
                "  \"codigoDoCargo\": 1,\n" +
                "  \"descricaoAtividade\": \"teste\",\n" +
                "  \"dadosPessoaisRequest\": {\n" +
                "    \"nomeCompleto\": \"Daniel Lopes Sousa Moreira\",\n" +
                "    \"dataNascimento\": \"02/08/1999\",\n" +
                "    \"nomeDaMae\": \"Eronice Cristina Lopes Sousa Moreira\",\n" +
                "    \"nomeDoPai\": \"Paulo Roberto Moreira\"\n" +
                "  },\n" +
                "  \"enderecoRequest\": {\n" +
                "    \"cep\": \"06385820\",\n" +
                "    \"logradouro\": \"string\",\n" +
                "    \"numero\": \"141\",\n" +
                "    \"bairro\": \"string\",\n" +
                "    \"localidade\": \"string\",\n" +
                "    \"uf\": \"string\",\n" +
                "    \"estado\": \"string\",\n" +
                "    \"complemento\": \"string\"\n" +
                "  },\n" +
                "  \"documentosRequestList\": [\n" +
                "    {\n" +
                "      \"numeroDocumento\": \"528012563\",\n" +
                "      \"dataExpedicao\": \"20/10/2015\",\n" +
                "      \"dataValidade\": \"20/10/2025\",\n" +
                "      \"orgaoEmissor\": \"Estado de são paulo\",\n" +
                "      \"arquivoBase64\": \"\",\n" +
                "      \"tipoDocumento\": \"RG\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"numeroDocumento\": \"45083219840\",\n" +
                "      \"dataExpedicao\": \"20/10/2015\",\n" +
                "      \"dataValidade\": \"20/10/2025\",\n" +
                "      \"orgaoEmissor\": \"Estado de são paulo\",\n" +
                "      \"arquivoBase64\": \"\",\n" +
                "      \"tipoDocumento\": \"CPF\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"telefones\": [\n" +
                "    {\n" +
                "      \"dd\": \"11\",\n" +
                "      \"telefone\": \"985850569\",\n" +
                "      \"observacoes\": \"\",\n" +
                "      \"tipoContato\": \"COMERCIAL\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"emails\": [\n" +
                "    {\n" +
                "      \"email\": \"daniellsmoreira8741@gmail.com\",\n" +
                "      \"observacoes\": \"string\",\n" +
                "      \"tipoContato\": \"COMERCIAL\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }


    @Test
    void deveListarTodosOsColaboradores() throws Exception {
        List<ColaboradorResponseList> lista = new ArrayList<>();
        lista.add(colaboradorResponseList);
        when(gerenciarColaboradorCasoDeUso.listaDeColaboradores())
                .thenReturn(lista);

        mockMvc.perform(get("/api/colaboradores/lista"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].dadosPessoais.nomeCompleto").value("Daniel Lopes Sousa Moreira"));

        verify(gerenciarColaboradorCasoDeUso).listaDeColaboradores();
    }

    @Test
    void deveBuscarColaboradorPorId() throws Exception {
        Long codigoColaborador = 1L;
        when(gerenciarColaboradorCasoDeUso.buscarColaboradorPorId(codigoColaborador))
                .thenReturn(this.colaboradorResponseList);

        mockMvc.perform(get("/api/colaboradores/buscar")
                        .param("codigoColaborador", String.valueOf(codigoColaborador)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dadosPessoais.nomeCompleto").value("Daniel Lopes Sousa Moreira"));

        verify(gerenciarColaboradorCasoDeUso).buscarColaboradorPorId(codigoColaborador);
    }

    @Test
    void deveAtualizarColaboradorComSucesso() throws Exception {
        Long codigoColaborador = 1L;
        when(gerenciarColaboradorCasoDeUso.atualizarColaborador(any(Long.class), any(ColaboradorRequest.class)))
                .thenReturn(RetornoServicoBase.positivo("Atualizado com sucesso"));

        String requisicaoColaboradorJson = montarColaboradorRequest();

        mockMvc.perform(put("/api/colaboradores/atualizar")
                        .param("codigoColaborador", String.valueOf(codigoColaborador))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requisicaoColaboradorJson))
                .andExpect(status().isNoContent());

        verify(gerenciarColaboradorCasoDeUso).atualizarColaborador(any(Long.class), any(ColaboradorRequest.class));
    }

    @Test
    void deveDeletarColaboradorComSucesso() throws Exception {
        Long codigoColaborador = 1L;
        when(gerenciarColaboradorCasoDeUso.deletarColaborador(codigoColaborador))
                .thenReturn(RetornoServicoBase.negativo("Deletado com sucesso"));

        mockMvc.perform(delete("/api/colaboradores/deletar")
                        .param("codigoColaborador", String.valueOf(codigoColaborador)))
                .andExpect(status().isNoContent());

        verify(gerenciarColaboradorCasoDeUso).deletarColaborador(codigoColaborador);
    }





}