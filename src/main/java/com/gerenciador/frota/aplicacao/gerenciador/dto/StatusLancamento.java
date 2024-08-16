package com.gerenciador.frota.aplicacao.gerenciador.dto;

import lombok.Getter;

@Getter
public enum StatusLancamento {
    EM_ABERTO("Lancamento em aberto"),
    AGUARDANDO_FATURAMENTO("Finalizado Aguardando envio do lancamento e as faturas  para contabilidade"),
    AGUARDANDO_PAGAMENTO("Aguardando o pagamento do lancamento"),
    PAGAMENTO_CONCLUIDO("Pagamento ja foi realizado pela contabilidade");

    private String descricao;

    StatusLancamento(String descricao) {
        this.descricao = descricao;
    }
}
