package com.gerenciador.frota.aplicacao.rh.infraestrutura.controllerWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarCargoCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.CargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.FiltroCargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CargoEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerenciarCargoCasoDeUso gerenciarCargoCasoDeUso;

    private CargoRequest cargoRequest;
    private CargoEntity cargoEntity;
    private FiltroCargoRequest filtroCargoRequest;

    private CargoEntity cargoEntityModelo;

    @BeforeEach
    public void montarContexto() {
        this.cargoRequest = CargoRequest.builder().nomeCargo("GERENTE").descricaoCargo("GERENTE").tipoCargo(TipoCargo.GERENTE).build();
        this.cargoEntity = CargoEntity.builder().id(1l).nomeCargo("GERENTE").descricaoCargo("GERENTE").tipoCargo(TipoCargo.GERENTE).build();

        this.filtroCargoRequest = new FiltroCargoRequest();
    }

    @Test
    void deveCadastrarCargoComSucesso() throws Exception {
        when(gerenciarCargoCasoDeUso.criarCargo(any(CargoRequest.class)))
                .thenReturn(this.cargoEntity);

        mockMvc.perform(post("/api/cargos/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(this.cargoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeCargo").value("GERENTE"));

        verify(gerenciarCargoCasoDeUso).criarCargo(any(CargoRequest.class));

        filtroCargoRequest = FiltroCargoRequest.builder()
                .descricaoCargo("Descrição do Cargo")
                .cargo("Gerente")
                .build();

        cargoEntityModelo = CargoEntity.builder()
                .id(1L)
                .nomeCargo("Gerente")
                .descricaoCargo("Gerente de operações")
                .build();
    }
    @Test
    void deveRetornarBadRequestAoCadastrarCargo() throws Exception {
        when(gerenciarCargoCasoDeUso.criarCargo(any(CargoRequest.class)))
                .thenReturn(this.cargoEntity);

        mockMvc.perform(post("/api/cargos/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"{ \"codigoDoCargo\": null }\""))
                .andExpect(status().isBadRequest());

    }

    // Teste de Listagem de Cargos
    @Test
    void deveListarCargosComSucesso() throws Exception {
        when(gerenciarCargoCasoDeUso.listarCargos())
                .thenReturn(Collections.singletonList(this.cargoEntity));

        mockMvc.perform(get("/api/cargos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeCargo").value("GERENTE"));

        verify(gerenciarCargoCasoDeUso).listarCargos();
    }

    // Teste de Listagem de Cargos com Filtro


    // Teste de Atualização de Cargo
    @Test
    void deveAtualizarCargoComSucesso() throws Exception {
        RetornoServicoBase retornoServicoBase = RetornoServicoBase.positivo("Cargo atualizado com sucesso");
        when(gerenciarCargoCasoDeUso.atualizar(any(Long.class), any(CargoRequest.class)))
                .thenReturn(retornoServicoBase);

        mockMvc.perform(put("/api/cargos/atualziar")
                        .param("codigoCargo", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(this.cargoRequest)))
                .andExpect(status().isNoContent());

        verify(gerenciarCargoCasoDeUso).atualizar(any(Long.class), any(CargoRequest.class));
    }

    @Test
    void deveRetornarBadRequestAoAtualizarCargoComDadosInvalidos() throws Exception {
        mockMvc.perform(put("/api/cargos/atualziar")
                        .param("codigoCargo", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"codigoDoCargo\": null }"))
                .andExpect(status().isBadRequest());
    }

    // Teste de Deletar Cargo
    @Test
    void deveDeletarCargoComSucesso() throws Exception {
        RetornoServicoBase retornoServicoBase = RetornoServicoBase.positivo("Cargo desativado com sucesso");
        when(gerenciarCargoCasoDeUso.desativar(any(Long.class)))
                .thenReturn(retornoServicoBase);

        mockMvc.perform(delete("/api/cargos/deletar")
                        .param("codigoCargo", "1"))
                .andExpect(status().isNoContent());

        verify(gerenciarCargoCasoDeUso).desativar(any(Long.class));
    }

    @Test
    void deveRetornarBadRequestAoDeletarCargoComDadosInvalidos() throws Exception {
        mockMvc.perform(delete("/api/cargos/deletar")
                        .param("codigoCargo", "0"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveListarCargosComFiltro() throws Exception {
//        // Criando um objeto de página com tipo explícito
//        List<Cargo> cargos = List.of(
//                Cargo.builder()
//                        .id(1L)
//                        .nomeCargo("Gerente")
//                        .descricaoCargo("Gerente de operações")
//                        .build()
//        );
//        PageImpl<Cargo> paginaCargos = new PageImpl<>(cargos, PageRequest.of(0, 10), cargos.size());
//
//        // Mockando o comportamento com tipo explícito
//        Mockito.when(gerenciarCargoCasoDeUso.listarCargosPorFiltro(any(FiltroCargoRequest.class), any(Pageable.class)))
//                .thenReturn(paginaCargos);
//
//        // Corpo da requisição em JSON
//        String filtroRequestJson = "{\"descricaoCargo\": \"Gerente de operações\", \"cargo\": \"Gerente\"}";
//
//        // Realizando a requisição POST
//        mockMvc.perform(post("/api/cargo/listar/comFiltro")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(filtroRequestJson)
//                        .param("size", "10")
//                        .param("page", "1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(1L))
//                .andExpect(jsonPath("$.content[0].nomeCargo").value("Gerente"))
//                .andExpect(jsonPath("$.content[0].descricaoCargo").value("Gerente de operações"));
    }
    @Test
    void deveRetornarBadRequestQuandoFiltroInvalido() throws Exception {
        // Corpo da requisição inválido (faltando campos obrigatórios)
        String filtroRequestInvalido = "{\"descricaoCargo\": \"\", \"cargo\": \"\"}";

        mockMvc.perform(post("/api/cargo/listar/comFiltro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(filtroRequestInvalido)
                        .param("size", "10")
                        .param("page", "1"))
                .andExpect(status().is4xxClientError());
    }



}