package com.gerenciador.frota.aplicacao.logistica.adapters.inbound.controller;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso.GerenciarRemessaCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe responsável por expor os endpoints relacionados a Remessa e suas manutenção
 *
 * <p>Esta classe faz parte da camada de infraestrutura do sistema, conectando as requisições
 * feitas via API REST com os serviços de aplicação e domínio.</p>
 *
 * @author Daniel Lopes
 * @version 1.0
 */
@Tag(name = "Gestão de Logistica", description = "Controladores de gestao para o setor logistico.")
@RestController
@RequestMapping("/api/remessa")
@RequiredArgsConstructor
@Validated
public class RemessaController {

    private final GerenciarRemessaCasoDeUso gerenciarRemessaCasoDeUso;

    /**
     * Endpoint para cadastrar uma nova remessa.
     *
     * <p>Este método recebe um objeto {@link RemessaRequest} validado, processa a criação
     * de uma nova remessa no sistema e retorna a entidade remessa.</p>
     *
     * @return {@link ResponseEntity<  JpaRemessaEntity  >} contendo a viagem criado e o status HTTP 201 (CREATED).
     */
    @Operation(summary = "Cadastrar uma nova Remessa", description = "Endpoint deve cadastrar uma nova Remessa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Remessa cadastrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<RemessaResponse> cadastrarNovaRemessa(@RequestBody RemessaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciarRemessaCasoDeUso.cadastrarNovaRemessa(request));
    }


    /**
     * Endpoint para listar todas todas as remessas
     *
     * <p>Este método não recebe parâmetros de entrada.</p>
     *
     * @return {@link ResponseEntity<List<  JpaRemessaEntity  >>} contendo a lista de remessas e retornando o status HTTP 200 (OK).
     */
    @Operation(summary = "Listar todas as remessas", description = "Endpoint deve retornar uma lista de remessas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar as remessas com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Remessa>> listarTodasRemessas() {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarRemessaCasoDeUso.listarTodasRemessas());
    }

    /**
     * Endpoint para buscar codigo da remessa
     *
     * <p>Este método recebe parâmetros um codigo da remessa {@link Long codigoRemessa}.</p>
     *
     * @return {@link ResponseEntity<  JpaRemessaEntity  >} contendo a remessas e retornando o status HTTP 200 (OK).
     */
    @Operation(summary = "Buscar a remessa por codigo.", description = "Endpoint deve retornar remessas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Remessas encontrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/busca/porId")
    public ResponseEntity<Remessa> buscarRemessaPorId(@RequestParam Long codigoRemessa) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarRemessaCasoDeUso.buscarRemessaPorId(codigoRemessa));
    }

    /**
     * Endpoint atualizar a remessa
     *
     * <p>Este método recebe parâmetros um codigo da remessa {@link Long codigoRemessa} e o corpo de requisição
     * {@link RemessaRequest}.</p>
     *
     * @return {@link ResponseEntity<RetornoServicoBase>} o atualizar retorna um objeto que é com uma especie de feedback
     * o status HTTP 204 (NO_CONTENT).
     */
    @Operation(summary = "Atualizar a remessa por codigo.", description = "Endpoint deve atualizar a remessa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atualizar remessa e retornar um feedback."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PutMapping("/atualizar")
    public ResponseEntity<RetornoServicoBase> atualizarRemessa(@RequestParam Long codigoRemessa,
                                                                      @RequestBody RemessaRequest remessaRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gerenciarRemessaCasoDeUso.atualziarRemessaPorId(codigoRemessa, remessaRequest));
    }

    /**
     * Endpoint Deletar a remessa
     *
     * <p>Este método recebe parâmetros um codigo da remessa {@link Long codigoRemessa}.</p>
     *
     * @return {@link ResponseEntity<RetornoServicoBase>} o deletar retorna um objeto que é com uma especie de feedback
     * o status HTTP 204 (NO_CONTENT).
     */
    @Operation(summary = "Atualizar a remessa por codigo.", description = "Endpoint deve atualizar a remessa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletar remessa e retornar um feedback."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @DeleteMapping("/deletar")
    public ResponseEntity<RetornoServicoBase> deletarRemessasPorId(@RequestParam Long codigoRemessa) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarRemessaCasoDeUso.deletarRemessa(codigoRemessa));
    }
}
