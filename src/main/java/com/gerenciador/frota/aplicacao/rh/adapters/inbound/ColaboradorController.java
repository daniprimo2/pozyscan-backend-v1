package com.gerenciador.frota.aplicacao.rh.adapters.inbound;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarColaboradorCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.ColaboradorRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.ColaboradorResponseList;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe responsável por expor os endpoints relacionados ao gerenciamento de colaboradores.
 *
 * <p>Esta classe faz parte da camada de infraestrutura do sistema, conectando as requisições
 * feitas via API REST com os serviços de aplicação e domínio.</p>
 *
 * @author Daniel Lopes
 * @version 1.0
 */
@Tag(name = "Gestão de Rh", description = "Controladores de gestao para Recursos Humanos")
@RestController
@RequestMapping("/api/colaboradores")
@RequiredArgsConstructor
@Validated
public class ColaboradorController {

    private final GerenciarColaboradorCasoDeUso gerenciarColaboradorCasoDeUso;

    /**
     * Endpoint para cadastrar um novo colaborador.
     *
     * <p>Este método recebe um objeto {@link ColaboradorRequest} validado, processa a criação
     * de um novo colaborador no sistema e retorna o colaborador criado.</p>
     *
     * @return {@link ResponseEntity} contendo o cargo criado e o status HTTP 201 (CREATED).
     */
    @Operation(summary = "Cadastrar um novo Colaborador", description = "Endpoint deve cadastrar umm novo Colaborador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Colaborador cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<ColaboradorEntity> cadastrarNovoColaborador(@RequestBody @Valid ColaboradorRequest colaboradorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciarColaboradorCasoDeUso.criarColaborador(colaboradorRequest));
    }


    /**
     * Endpoint para buscar TODOS os colaboradores registrados.
     *
     * <p>Este método não recebe nenhum objeto.</p>
     *
     * @return {@link ResponseEntity} retornando uma lista de colaboradores
     *
     * e o status HTTP 200 (OK).
     */
    @Operation(summary = "Listar todos os colaboradores", description = "Endpoint deve listar Colaboradores.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de colaboradores."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/lista")
    public ResponseEntity<List<ColaboradorResponseList>> listarColaboradores() {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarColaboradorCasoDeUso.listaDeColaboradores());
    }


    @Operation(summary = "Pesquisar um colaborador pelo codigo.", description = "É uma busca realizada utilizando o codigo do colaborador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar todos os colaboradores."),
            @ApiResponse(responseCode = "400", description = "Erro de validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/buscar")
    public ResponseEntity<ColaboradorResponseList> buscarColaboradorPorId(@RequestParam Long codigoColaborador) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarColaboradorCasoDeUso.buscarColaboradorPorId(codigoColaborador));
    }


    @Operation(summary = "Atualizar os dados de um colaborador existente.", description = "Endpoint atualiza informações do colaborador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dados do colaborador foi atualizados com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PutMapping("/atualizar")
    public ResponseEntity<RetornoServicoBase> atualizarColaboradorPorId(@RequestParam Long codigoColaborador, @RequestBody ColaboradorRequest colaboradorRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gerenciarColaboradorCasoDeUso.atualizarColaborador(codigoColaborador, colaboradorRequest));
    }


    @Operation(summary = "Deletar um colaborador.", description = "Endpoint para deletar um colaborador informando o seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Colaborador foi deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @DeleteMapping("/deletar")
    public ResponseEntity<RetornoServicoBase> deletarColaboradorPorId(@RequestParam Long codigoColaborador) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gerenciarColaboradorCasoDeUso.deletarColaborador(codigoColaborador));
    }



}
