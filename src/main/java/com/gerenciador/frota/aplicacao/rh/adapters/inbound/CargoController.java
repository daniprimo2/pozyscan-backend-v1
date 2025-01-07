package com.gerenciador.frota.aplicacao.rh.adapters.inbound;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarCargoCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.CargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.FiltroCargoRequest;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe responsável por expor os endpoints relacionados ao gerenciamento de cargos.
 *
 * <p>Esta classe faz parte da camada de infraestrutura do sistema, conectando as requisições
 * feitas via API REST com os serviços de aplicação e domínio.</p>
 *
 * @author Daniel Lopes
 * @version 1.0
 */
@Tag(name = "Gestão de Rh", description = "Controladores de gestao para Recursos Humanos")
@RestController
@RequestMapping("/api/cargos")
@RequiredArgsConstructor
@Validated
public class CargoController {

    private final GerenciarCargoCasoDeUso gerenciarCargoCasoDeUso;


    /**
     * Endpoint para cadastrar um novo cargo.
     *
     * <p>Este método recebe um objeto {@link CargoRequest} validado, processa a criação
     * de um novo cargo no sistema e retorna o cargo criado.</p>
     *
     * @param cargoRequest Objeto contendo os dados do cargo a ser cadastrado.
     *                     Este objeto é validado automaticamente usando as anotações
     *                     do pacote jakarta.validation.
     * @return {@link ResponseEntity} contendo o cargo criado e o status HTTP 201 (CREATED).
     */
    @Operation(summary = "Cadastrar um novo Cargo", description = "Endpoint deve cadastrar um novo cargo na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cargo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro Interno")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<CargoEntity> cadastrarNovoCargo(@RequestBody @Valid CargoRequest cargoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciarCargoCasoDeUso.criarCargo(cargoRequest));
    }

    /**
     * Endpoint para listar apartir de um filtro cargos.
     *
     * <p>Este método recebe a requisiçãoe e retorna uma lista dos cargos.</p>
     *
     * @return {@link ResponseEntity} contendo todos os cargos
     * e o status HTTP 200 (OK).
     */
    @Operation(summary = "Buscar a lista dos cargos a partir de um filtro.", description = "Endpoint deve listar todos os cargos a partir de parametros de um filtro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista os cargos"),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<CargoEntity>> listarOsCargos() {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarCargoCasoDeUso.listarCargos());
    }


    /**
     * Endpoint para listar todos os cargos a partir de um filtro.
     *
     * <p>Este método recebe um objeto {@link FiltroCargoRequest} com os campos do filtro
     * a requisiçãoe e retorna uma lista dos cargos.</p>
     *
     * @return {@link ResponseEntity} contendo todos os a partir dos parêmetros adicionado
     * retorna uma lista de cargos
     * e o status HTTP 200 (OK).
     */
    @Operation(summary = "Buscar a lista dos cargos a partir de um filtro.", description = "Endpoint deve listar todos os cargos a partir de parametros de um filtro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista dos cargos filtrados."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PostMapping("/listar/comFiltro")
    public ResponseEntity<PageImpl<?>> listarOsCargos(@RequestBody @Valid FiltroCargoRequest filtroCargoRequest,
                                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                                          @RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarCargoCasoDeUso.listarCargosPorFiltro(filtroCargoRequest, pageable));
    }

    /**
     * Endpoint para atualizar os cargos..
     *
     * <p>Este método recebe O objeto {@link CargoRequest} e o codigo do cargo que deve ser atualizado
     * {@link Long id}.</p>
     *
     * @return {@link ResponseEntity}
     *
     * e o status HTTP 204 (NO_CONTENT).
     */
    @Operation(summary = "Atualizar o cadastro do cargo", description = "Endpoint atualizar as informações de cargo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucesso na requisição de atualizar um cargo."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PutMapping("/atualziar")
    public ResponseEntity<RetornoServicoBase> atualziar(@RequestParam Long codigoCargo, @RequestBody @Valid CargoRequest cargoRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gerenciarCargoCasoDeUso.atualizar(codigoCargo, cargoRequest));
    }

    /**
     * Endpoint para atualizar os cargos para desativado.
     *
     * <p>Este método recebe O codigo do cargo {@link Long} que deve ser excluido.
     *
     * @return {@link ResponseEntity}
     *
     * e o status HTTP 204 (NO_CONTENT).
     */
    @Operation(summary = "Deletar cargo", description = "Endpoint deletar as informações de cargo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucesso ao deletar o cargo."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @DeleteMapping("/deletar")
    public ResponseEntity<RetornoServicoBase> desativar(@RequestParam Long codigoCargo) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gerenciarCargoCasoDeUso.desativar(codigoCargo));
    }


}
