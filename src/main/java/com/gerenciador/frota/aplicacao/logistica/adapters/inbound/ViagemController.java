package com.gerenciador.frota.aplicacao.logistica.adapters.inbound;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso.GerenciarViagemCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ViagemRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Classe responsável por expor os endpoints relacionados ao gerenciamento das viagens programadas e outras atualizacoes
 *
 * <p>Esta classe faz parte da camada de infraestrutura do sistema, conectando as requisições
 * feitas via API REST com os serviços de aplicação e domínio.</p>
 *
 * @author Daniel Lopes
 * @version 1.0
 */
@Tag(name = "Gestão de Logistica", description = "Controladores de gestao para o setor logistico.")
@RestController
@RequestMapping("/api/viagem")
@RequiredArgsConstructor
@Validated
public class ViagemController {

    private final GerenciarViagemCasoDeUso gerenciarViagemCasoDeUso;

    /**
     * Endpoint para cadastrar uma nova viagem
     *
     * <p>Este método recebe um objeto {@link ViagemRequest} validado, processa a criação
     * de uma nova viagem no sistema e retorna a entidade viagem criada.</p>
     *
     * @return {@link ResponseEntity} contendo a viagem criado e o status HTTP 201 (CREATED).
     */
    @Operation(summary = "Cadastrar uma nova viagem", description = "Endpoint deve cadastrar uma nova Viagem.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Viagem cadastrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Viagem> cadastrarNovaViagem(@RequestBody ViagemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciarViagemCasoDeUso.cadastrarNovaViagem(request));
    }

    /**
     * Endpoint para listar todas as viagens registradas
     *
     * <p>Este método não recebe parâmetros de entrada.</p>
     *
     * @return {@link ResponseEntity} contendo a listas de viagems e retornando o status HTTP 200 (OK).
     */
    @Operation(summary = "Listar todas as viagens", description = "Endpoint deve retornar uma lista de viagens cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar as viagems com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Viagem>> listarViagens() {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarViagemCasoDeUso.listarTodasAsViagems());
    }

    /**
     * Endpoint para listar viagem com filtro.
     *
     * <p>Este método não recebe o objeto {@link FiltroViagemRequest} mais dois parâmetros int para instruções a paginação page e size.</p>
     *
     * @return {@link ResponseEntity} contendo a listas de viagems filtradas e paginadas e retornando o status HTTP 200 (OK).
     */
    @Operation(summary = "Listar viagem com filtro.", description = "Endpoint deve receber informções de reqiest, page e size e traz filtrada pelas informações adicionadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtro das viagems com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PostMapping("/listar/comFiltro")
    public ResponseEntity<PageImpl<?>> listarViagensComFiltro(@RequestBody FiltroViagemRequest filtroRequest,
                                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarViagemCasoDeUso.listarComFiltroViagem(filtroRequest, page, size));
    }

    @PostMapping("/listar/comFiltro/puro")
    public ResponseEntity<List<JpaViagemEntity>> listarViagensComFiltroPuro(@RequestBody FiltroViagemRequest filtroRequest) {
        if (filtroRequest.getTipoViagem() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.emptyList()); // ou uma mensagem de erro explicativa
        }
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarViagemCasoDeUso.listarComFiltroViagem(filtroRequest));
    }


    /**
     * Endpoint deve deletar uma viagem
     *
     * <p>Este método recebe o um Long codigo de viagem.</p>
     *
     * @return {@link ResponseEntity} contendo a listas de viagems e retornando o status HTTP 200 (OK).
     */
    @Operation(summary = "Deletar viagem por id. ", description = "Endpoint deve deletar a viagem e retornar um retorno service base.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viagems deletada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @DeleteMapping("/deletar")
    public ResponseEntity<RetornoServicoBase> deletarViagemPorCodigo(@RequestParam Long codigoViagem) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gerenciarViagemCasoDeUso.deletarViagemPorCodigo(codigoViagem));
    }

}
