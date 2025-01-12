package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoRequest {

    private String nomeProduto;

    private String descricaoProduto;

    private TipoProduto tipoProduto;

    private int quantidade;

    private PesoProdutoRequest pesoProdutoRequest;
    private DimensaoProdutoRequest dimensaoProdutoRequest;  
    private PrecoProdutoRequest precoProdutoRequest;







}
