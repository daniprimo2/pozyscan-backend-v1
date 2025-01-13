package com.gerenciador.frota.aplicacao.logistica.adapters.inbound;


import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso.GerenciarProdutoCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;
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
 * Classe responsável por expor os endpoints relacionados a os Produtos e suas manutenção
 *
 * <p>Esta classe faz parte da camada de infraestrutura do sistema, conectando as requisições
 * feitas via API REST com os serviços de aplicação e domínio.</p>
 *
 * @author Daniel Lopes
 * @version 1.0
 */
@Tag(name = "Gestão de Logistica", description = "Controladores de gestao para o setor logistico.")
@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
@Validated
public class ProdutoController {

    private final GerenciarProdutoCasoDeUso gerenciarProdutoCasoDeUso;

    /**
     * Endpoint para cadastrar um novo Produto.
     *
     * <p>Este método recebe um objeto {@link Produto} validado, processa a criação
     * de um novo produto no sistema e retorna a entidade produto que foi salvo.</p>
     *
     * @return {@link ResponseEntity < Produto >} contendo o produto criado e o status HTTP 201 (CREATED).
     */
    @Operation(summary = "Cadastrar um novo Produto", description = "Endpoint deve cadastrar umo novo promesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> cadastrarNovoProduto(@RequestBody ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciarProdutoCasoDeUso.cadastrarNovaProduto(request));
    }

    /**
     * Endpoint para listar todos os produtos.
     *
     * <p>Este método não recebe parâmetros de entrada.</p>
     *
     * @return {@link ResponseEntity< List <   Produto   >>} contendo a lista de produtos e retornando o status HTTP 200 (OK).
     */
    @Operation(summary = "Listar todos os produtos", description = "Endpoint deve retornar uma lista de produtos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar os produtos com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listarTodasRemessas() {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarProdutoCasoDeUso.listaDeProdutos());
    }


    /**
     * Endpoint para buscar um produto pelo codigo dele.
     *
     * <p>Este método recebe parâmetros um codigo de produto {@link Long codigoProduto}.</p>
     *
     * @return {@link ResponseEntity<  Produto  >} contendo a remessas e retornando o status HTTP 200 (OK).
     */
    @Operation(summary = "Buscar o produto por codigo.", description = "Endpoint deve retornar um produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @GetMapping("/busca/porId")
    public ResponseEntity<Produto> buscarProdutoPorId(@RequestParam Long codigoProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarProdutoCasoDeUso.buscarProdutoPorId(codigoProduto));
    }

    /**
     * Endpoint atualizar o produto.
     *
     * <p>Este método recebe parâmetros um codigo da produto {@link Long codigoProduto} e o corpo de requisição
     * {@link ProdutoRequest}.</p>
     *
     * @return {@link ResponseEntity< RetornoServicoBase >} o atualizar retorna um objeto que é com uma especie de feedback
     * o status HTTP 204 (NO_CONTENT).
     */
    @Operation(summary = "Atualizar o produto por codigo.", description = "Endpoint deve atualizar o produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atualizar produto e retornar um feedback."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @PutMapping("/atualizar")
    public ResponseEntity<RetornoServicoBase> atualizarProduto(@RequestParam Long codigoProduto,
                                                               @RequestBody ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gerenciarProdutoCasoDeUso.atualizarProdutoPorId(codigoProduto, request));
    }

    /**
     * Endpoint Deletar o produto.
     *
     * <p>Este método recebe parâmetros um codigo do produto {@link Long codigoProduto}.</p>
     *
     * @return {@link ResponseEntity<RetornoServicoBase>} o deletar retorna um objeto que é com uma especie de feedback
     * o status HTTP 204 (NO_CONTENT).
     */
    @Operation(summary = "Atualizar o produto por codigo.", description = "Endpoint deve atualizar o produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletar produto e retornar um feedback."),
            @ApiResponse(responseCode = "400", description = "Erro validacao.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    @DeleteMapping("/deletar")
    public ResponseEntity<RetornoServicoBase> deletarProdutoPorId(@RequestParam Long codigoProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenciarProdutoCasoDeUso.deletarProduto(codigoProduto));
    }
}
