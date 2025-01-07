package com.gerenciador.frota.aplicacao.logistica.adapters.inbound;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso.GerenciarNotaFiscalCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
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

/**
 * Classe responsável por expor os endpoints relacionados a
 * Nota Fiscal e manter os seus dados.
 *
 * <p>Esta classe faz parte da camada de infraestrutura do sistema, conectando as requisições
 * feitas via API REST com os serviços de aplicação e domínio.</p>
 *
 * @author Daniel Lopes
 * @version 1.0
 */
@Tag(name = "Gestão de Logistica", description = "Controladores de gestao para o setor logistico.")
@RestController
@RequestMapping("/api/notaFiscalLogistica")
@RequiredArgsConstructor
@Validated
public class NotaFiscalLogiscaController {

    private final GerenciarNotaFiscalCasoDeUso gerenciarNotaFiscalCasoDeUso;

    /**
     * Endpoint para cadastrar nota fiscal.
     *
     * <p>Este método recebe um objeto {@link NotaFiscalLogisticaRequest} validado, processa a criação
     * de uma nova nota fiscal no sistema e retorna a Nota Fiscal.</p>
     *
     * @return {@link ResponseEntity<JpaNotaFiscalLogisticaEntity>} contendo a viagem criado e o status HTTP 201 (CREATED).
     */
    @Operation(summary = "Registrar uma nova nota fiscal", description = "Endpoint deve cadastrar uma nova nota fiscal.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota fiscal registrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<JpaNotaFiscalLogisticaEntity> cadastrarNotaFiscal(@RequestBody NotaFiscalLogisticaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciarNotaFiscalCasoDeUso.registrarNotaFiscal(request));
    }

    /**
     * Endpoint para buscar a  nota fiscal por codigo.
     *
     * <p>Este método recebe um codigo {@link Long} processa a criação
     * de uma nova nota fiscal no sistema e retorna a Nota Fiscal.</p>
     *
     * @return {@link ResponseEntity<  JpaNotaFiscalLogisticaEntity  >} encontrando o codigo da nota fiscal
     * retorna com status HTTP 201 (CREATED).
     */
    @Operation(summary = "Buscar nota fiscal pelo codigo da nota fiscal",
            description = "Endpoint deve buscar nota fiscal por um codigo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buscar nota fiscal registrada pelo codigo da NF sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/buscar")
    public ResponseEntity<JpaNotaFiscalLogisticaEntity> buscarNotaFiscalPorId(@RequestParam Long codigoNotaFiscal) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarNotaFiscalCasoDeUso.buscarNotaPeloCodigo(codigoNotaFiscal));
    }

    /**
     * Endpoint para atualizar a  nota fiscal por codigo.
     *
     * <p>Este método recebe um codigo {@link Long} e {@link NotaFiscalLogisticaRequest}
     * atualiza uma nota fiscal ja criada. </p>
     *
     * @return {@link ResponseEntity< RetornoServicoBase >} encontrando o codigo da nota fiscal
     * retorna com status HTTP 204 (NO_CONTENT).
     */
    @Operation(summary = "Atualizar a nota fiscal pelo codigo da nota fiscal",
            description = "Endpoint deve atualziar a nota fiscal por um codigo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atualizar a nota fiscal com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PutMapping("/atualizar")
    public ResponseEntity<RetornoServicoBase> atualizarFiscalPorId(@RequestParam Long codigoNotaFiscal,
                                                                    @RequestBody NotaFiscalLogisticaRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarNotaFiscalCasoDeUso.atualizarNotaFiscal(codigoNotaFiscal, request));
    }

    /**
     * Endpoint para deletar a  nota fiscal por codigo.
     *
     * <p>Este método recebe um codigo {@link Long} deletar uma nota fiscal.</p>
     *
     * @return {@link ResponseEntity< RetornoServicoBase >} encontrando o codigo da nota fiscal
     * retorna com status HTTP 204 (NO_CONTENT).
     */
    @Operation(summary = "Deletar a nota fiscal pelo codigo da nota fiscal",
            description = "Endpoint deve deletar a nota fiscal por um codigo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atualizar a nota fiscal com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @DeleteMapping("/deletar")
    public ResponseEntity<RetornoServicoBase> deletarFiscalPorId(@RequestParam Long codigoNotaFiscal) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarNotaFiscalCasoDeUso.deletarNotaFiscasl(codigoNotaFiscal));
    }

}
