package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DadosPessoaisEntity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColaboradorTest {

    private Colaborador colaborador;

    private DadosPessoais dadosPessoais;
    private Cargo cargo;
    private List<Telefones> telefones;
    private List<Email> emails;
    private List<Documentos> documentos;


    private Endereco endereco;


    @BeforeEach
    void setUp() {

        endereco = new Endereco(6l, "06385820", "Rua Ipixuna", "154", "Vila Menk", "Carapicuiba", "SP", "São Paulo", "");
        dadosPessoais = new DadosPessoais(2l, "Daniel Lopes Sousa Moreira", "05/08/2024", "Eronice Cristina", "Paulo Roberto", endereco);
        cargo = new Cargo(1L, "Analista", "Responsável por análise de dados", TipoCargo.ANALISTA);
        telefones = new ArrayList<>();
        telefones.add(new Telefones(1l, "11", "99999-9999", "Celular", TipoContato.PESSOAL, colaborador));

        emails = new ArrayList<>();
        emails.add(new Email(1l, "joao.silva@empresa.com", "", TipoContato.PESSOAL, colaborador));

        documentos = new ArrayList<>();
        documentos.add(new Documentos(1l, "123.456.789-00", "05/08/2024", "15/05/1985", "Estado de São Paulo", "", TipoDocumento.CPF, colaborador));

        colaborador = new Colaborador(
                1L,
                "Desenvolve atividades relacionadas ao sistema",
                StatusColaborador.ATIVO,
                "2023-01-01",
                null,
                dadosPessoais,
                cargo,
                telefones,
                emails,
                documentos
        );
    }

    @Test
    void deveCriarColaboradorComSucesso() {
        assertNotNull(colaborador);
        assertEquals(1L, colaborador.getId());
        assertEquals("Desenvolve atividades relacionadas ao sistema", colaborador.getDescricaoAtividade());
        assertEquals(StatusColaborador.ATIVO, colaborador.getStatus());
        assertEquals("2023-01-01", colaborador.getDataContratacao());
        assertNull(colaborador.getDataDemissao());
        assertEquals(dadosPessoais, colaborador.getDadosPessoais());
        assertEquals(cargo, colaborador.getCargo());
        assertEquals(telefones, colaborador.getTelefones());
        assertEquals(emails, colaborador.getEmailS());
        assertEquals(documentos, colaborador.getDocumentos());
    }

    @Test
    void deveAtualizarStatusComSucesso() {
        colaborador.setStatus(StatusColaborador.ATIVO);
        assertEquals(StatusColaborador.ATIVO, colaborador.getStatus());
    }

    @Test
    void deveAtualizarDataDemissaoComSucesso() {
        colaborador.setDataDemissao("2023-12-31");
        assertEquals("2023-12-31", colaborador.getDataDemissao());
    }

    @Test
    void deveAdicionarTelefoneComSucesso() {
        Telefones novoTelefone = new Telefones(1l, "11", "99999-9999", "Celular", TipoContato.PESSOAL, colaborador);
        colaborador.getTelefones().add(novoTelefone);

        assertEquals(2, colaborador.getTelefones().size());
        assertTrue(colaborador.getTelefones().contains(novoTelefone));
    }

    @Test
    void deveAdicionarEmailComSucesso() {
        Email novoEmail = new Email(1l, "joao.silva@empresa.com", "", TipoContato.PESSOAL, colaborador);
        colaborador.getEmailS().add(novoEmail);

        assertEquals(2, colaborador.getEmailS().size());
        assertTrue(colaborador.getEmailS().contains(novoEmail));
    }

    @Test
    void deveAdicionarDocumentoComSucesso() {
        Documentos novoDocumento = new Documentos(1l, "123.456.789-00", "05/08/2024", "15/05/1985", "Estado de São Paulo", "", TipoDocumento.CPF, colaborador);
        colaborador.getDocumentos().add(novoDocumento);

        assertEquals(2, colaborador.getDocumentos().size());
        assertTrue(colaborador.getDocumentos().contains(novoDocumento));
    }

    @Test
    void deveAtualizarDadosPessoaisComSucesso() {
        DadosPessoais novosDadosPessoais = new DadosPessoais(2l, "Daniel Lopes Sousa Moreira", "05/08/2024", "Eronice Cristina", "Paulo Roberto", endereco);
        colaborador.setDadosPessoais(novosDadosPessoais);

        assertEquals(novosDadosPessoais, colaborador.getDadosPessoais());
    }

    @Test
    void deveAtualizarCargoComSucesso() {
        Cargo novoCargo = new Cargo(2L, "Gerente", "Responsável pela equipe", TipoCargo.ADMINISTRATIVO);
        colaborador.setCargo(novoCargo);

        assertEquals(novoCargo, colaborador.getCargo());
    }

    @Test
    void deveCriarColaboradorUsandoConstrutorVazio() {
        Colaborador novoColaborador = new Colaborador();
        assertNull(novoColaborador.getId());
        assertNull(novoColaborador.getDescricaoAtividade());
        assertNull(novoColaborador.getStatus());
        assertNull(novoColaborador.getDataContratacao());
        assertNull(novoColaborador.getDataDemissao());
        assertNull(novoColaborador.getDadosPessoais());
        assertNull(novoColaborador.getCargo());
        assertNotNull(novoColaborador.getTelefones());
        assertNotNull(novoColaborador.getEmailS());
        assertNotNull(novoColaborador.getDocumentos());
    }
}
